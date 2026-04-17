package com.codewithmosh.store.payments;

import com.codewithmosh.store.entities.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PaymentResults {
    private long OrderId;
    private PaymentStatus status;

    public PaymentStatus getPaymentStatus() {
        return status;
    }
}
