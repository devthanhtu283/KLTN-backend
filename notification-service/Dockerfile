# Sử dụng Maven để build (Multi-stage build)
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Đặt thư mục làm việc
WORKDIR /app

# Copy toàn bộ project vào container
COPY . .

# Build ứng dụng với Maven (Bỏ qua test để build nhanh hơn)
RUN mvn clean package -DskipTests

# Stage 2: Chạy ứng dụng với OpenJDK 21
FROM eclipse-temurin:21-jdk AS runtime

# Đặt thư mục làm việc
WORKDIR /app

# Copy file JAR từ stage build
COPY --from=build /app/target/*.jar notification-service.jar

# Cổng được chỉ định trong Docker
EXPOSE 8084

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "notification-service.jar"]
