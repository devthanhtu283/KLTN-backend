package com.demo.services;

import com.demo.dtos.PaymentDTO;
import org.modelmapper.TypeToken;

import java.util.List;

public interface PaymentService {
    boolean create(PaymentDTO paymentDTO);

    long getTotalAmount();

    List<PaymentDTO> findAll() ;

}
