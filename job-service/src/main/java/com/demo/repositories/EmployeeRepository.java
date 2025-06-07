package com.demo.repositories;

import com.demo.entities.Employer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employer, Integer> {
    @Query(
            value = """
                    SELECT * FROM employer e 
                    WHERE (YEAR(CURDATE()) - CAST(e.founded_in AS UNSIGNED)) > 10 
                    AND CAST(e.team_member AS UNSIGNED) > 1000
                    """,
            countQuery = """
                    SELECT COUNT(*) FROM employer e 
                    WHERE (YEAR(CURDATE()) - CAST(e.founded_in AS UNSIGNED)) > 10 
                    AND CAST(e.team_member AS UNSIGNED) > 1000
                    """,
            nativeQuery = true
    )
    Page<Employer> findLargeCompanies(Pageable pageable);


    @Query(value = "SELECT * FROM employer e " +
            "WHERE NOT ((YEAR(CURDATE()) - CAST(e.founded_in AS UNSIGNED)) > 10 " +
            "AND CAST(e.team_member AS UNSIGNED) > 1000)",
            countQuery = "SELECT COUNT(*) FROM employer e " +
                    "WHERE NOT ((YEAR(CURDATE()) - CAST(e.founded_in AS UNSIGNED)) > 10 " +
                    "AND CAST(e.team_member AS UNSIGNED) > 1000)",
            nativeQuery = true)
    Page<Employer> findMediumAndSmallCompanies(Pageable pageable);

    List<Employer> findByCompanyNameContainingIgnoreCaseAndStatusTrue(String companyName);
}
