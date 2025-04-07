package com.demo.services;

import com.demo.dtos.PaymentDTO;

public interface PaymentService {
    boolean create(PaymentDTO paymentDTO);
}
