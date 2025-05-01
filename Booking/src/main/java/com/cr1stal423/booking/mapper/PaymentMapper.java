package com.cr1stal423.booking.mapper;

import com.cr1stal423.booking.DTO.PaymentDto;
import com.cr1stal423.booking.model.Payment;
import lombok.Data;

@Data
public class PaymentMapper {
    public static PaymentDto mapToPaymentDto(Payment payment, PaymentDto paymentDto){
        paymentDto.setPaymentStatus(payment.getPaymentStatus());
        paymentDto.setAmount(payment.getAmount());
        return paymentDto;
    }
    public static Payment mapToPayment(PaymentDto paymentDto, Payment payment){
        payment.setPaymentStatus(paymentDto.getPaymentStatus());
        payment.setAmount(paymentDto.getAmount());
        return payment;
    }
}
