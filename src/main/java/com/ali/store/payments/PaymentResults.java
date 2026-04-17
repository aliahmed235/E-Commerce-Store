package com.ali.store.payments;

import com.ali.store.entities.PaymentStatus;
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
