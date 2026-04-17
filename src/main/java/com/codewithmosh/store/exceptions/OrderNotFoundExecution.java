package com.codewithmosh.store.exceptions;

public class OrderNotFoundExecution extends RuntimeException {
        public OrderNotFoundExecution() {
            super("Order not found");
        }
}
