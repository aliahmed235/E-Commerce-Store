package com.codewithmosh.store.payments;

import com.codewithmosh.store.entities.Order;

import java.util.Optional;

public interface PaymentGateway {

    CheckoutSession createCheckoutSession(Order order);
    Optional<PaymentResults> ParseWebhookRequest(WebhookRequest request);
}
