package com.demo.services;

import com.demo.dtos.PaymentDTO;
import com.demo.entities.Employermembership;
import com.demo.entities.Payment;
import com.demo.repositories.PaymentRepository;
import com.netflix.discovery.converters.Auto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public boolean create(PaymentDTO paymentDTO) {
        try {
            Employermembership employermembership = new Employermembership();
            employermembership.setId(paymentDTO.getEmployerMembershipId());
            Payment payment = mapper.map(paymentDTO, Payment.class);
            payment.setPaymentMethod("VNPAY");
            payment.setStatus(true);
            payment.setTime(new Date());
            payment.setDescription("Thanh toán thành công số tiền " + paymentDTO.getAmount());
            Random random = new Random();
            int number = 100000 + random.nextInt(900000);
            payment.setTransactionId(number);
            payment.setPaymentType("BANKING");
            paymentRepository.save(payment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long getTotalAmount() {
        return paymentRepository.getTotalAmount();
    }

    @Override
    public List<PaymentDTO> findAll() {
        try {
            return mapper.map(paymentRepository.findAll(),
                    new TypeToken<List<PaymentDTO>>() {}.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
