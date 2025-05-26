package com.demo.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        logger.debug("Processing request for path: {}", path);

        // Danh sách các endpoint không cần token (phải khớp với SecurityConfig)
        String[] permittedPaths = {
                "/user/login", "/user/register", "/user/findByEmail/**", "/user/findById/**", "/python/**",
                "/job/findAll", "/job/category/**", "/job/experience/**", "/job/findAllPagination/**",
                "/job/findById/**", "/job/findByEmployerIdPagination/**", "/job/worktype/**",
                "/job/reviews/**", "/job/location/findAll", "/job/searchJobs", "/notification/**"
                , "/application/auth-url/**", "/application/check-auth/**", "/application/oauth-callback/**", "/application/create-event/**"
                , "/application/save-event/**", "/application/get-saved-event/**", "/user-static/**"
                , "/assets/**", "/user/employer/get-large-companies/**", "/user/**", "/user-static/assets/**", "/ws-chat/**", "/job-static/**", "/user-static/**", "/job/**"
        };

        // Kiểm tra xem path có nằm trong danh sách permitAll không
//        boolean isPermitted = false;
        boolean isPermitted = Arrays.stream(permittedPaths)
                .anyMatch(p -> pathMatcher.match(p, path));

//        for (String permittedPath : permittedPaths) {
//            if (permittedPath.endsWith("/**")) {
//                String prefix = permittedPath.substring(0, permittedPath.length() - 3);
//                if (path.startsWith(prefix)) {
//                    isPermitted = true;
//                    break;
//                }
//            } else {
//                if (path.equals(permittedPath)) {
//                    isPermitted = true;
//                    break;
//                }
//            }
//        }

        // Nếu endpoint nằm trong permitAll, bỏ qua kiểm tra token
        if (isPermitted || exchange.getRequest().getPath().toString().startsWith("/user-static/")) {
            logger.debug("Skipping authentication for permitted endpoint: {}", path);
            return chain.filter(exchange);
        }

        // Lấy token từ header
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        logger.debug("Authorization header: {}", authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("Missing or invalid Authorization header for path: {}", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);
        logger.debug("Extracted token: {}", token);

        // Xác minh token
        try {
            if (!jwtUtils.validateToken(token)) {
                logger.warn("Invalid JWT token for path: {}", path);
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // Trích xuất username từ token
            String username = jwtUtils.getUsernameFromToken(token);
            logger.debug("Token validated for user: {}", username);

            // Tạo authentication token và đặt vào SecurityContext
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    username, null, null);
            SecurityContextImpl securityContext = new SecurityContextImpl();
            securityContext.setAuthentication(authentication);

            // Thêm thông tin username và token gốc vào header
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(r -> r.headers(headers -> {
                        headers.set("X-Authenticated-User", username);
                        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                    }))
                    .build();

            logger.debug("Forwarding request with headers - X-Authenticated-User: {}, Authorization: Bearer <token>", username);

            // Đặt SecurityContext và tiếp tục chuỗi filter
            return chain.filter(modifiedExchange)
                    .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)))
                    .then(Mono.fromRunnable(() -> logger.debug("Request processed for path: {}", path)));
        } catch (Exception e) {
            logger.error("Error validating token for path: {} - {}", path, e.getMessage(), e);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}