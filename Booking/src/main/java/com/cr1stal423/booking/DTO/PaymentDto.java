package com.cr1stal423.booking.DTO;

import com.cr1stal423.booking.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class PaymentDto {

    private BigDecimal amount;

    private PaymentStatus paymentStatus;
}
