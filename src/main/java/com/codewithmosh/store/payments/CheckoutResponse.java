package com.codewithmosh.store.payments;

import lombok.Data;

@Data
public class CheckoutResponse {
    private Long OrderId;
    private String checkoutUrl;

    public CheckoutResponse(Long OrderId, String checkoutUrl) {
        this.OrderId = OrderId;
        this.checkoutUrl = checkoutUrl;
    }
}
