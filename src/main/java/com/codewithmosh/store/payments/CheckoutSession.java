package com.codewithmosh.store.payments;


import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class CheckoutSession {
    private String checkoutUrl;

    public String getCheckoutUrl() {
        return checkoutUrl;
    }
}
