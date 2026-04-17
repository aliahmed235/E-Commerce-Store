package com.ali.store.payments;

import com.ali.store.entities.Order;

import java.util.Optional;

public interface PaymentGateway {

    CheckoutSession createCheckoutSession(Order order);
    Optional<PaymentResults> ParseWebhookRequest(WebhookRequest request);
}
