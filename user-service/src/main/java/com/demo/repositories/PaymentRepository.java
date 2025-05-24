package com.demo.repositories;

import com.demo.entities.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

    @Query("SELECT SUM(p.amount) FROM Payment p")
    Long getTotalAmount();

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.status = true")
    Long getTotalAmountBySuccessStatus();

    @Query("SELECT p.paymentMethod, SUM(p.amount) FROM Payment p GROUP BY p.paymentMethod")
    Page<Object[]> getTotalAmountByPaymentMethod(Pageable pageable);

}
