package com.ali.store.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "date_created" , insertable = false, updatable = false)
    private LocalDate dateCreated;

    @OneToMany(mappedBy = "cart" , cascade = CascadeType.MERGE ,orphanRemoval = true, fetch =  FetchType.EAGER)
    private Set<CartItem> Items = new LinkedHashSet<>();


    public BigDecimal getTotalPrice() {
        return Items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public CartItem GetItem(Long productId) {
        return Items.stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public CartItem addItem(Product product) {

        var cartItem = GetItem(product.getId());

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setCart(this);
            Items.add(cartItem);
        }
        return  cartItem;
    }
    public void  removeItem(Long productId) {
        var cartItem = GetItem(productId);
        if (cartItem != null) {
            getItems().remove(cartItem);
            cartItem.setCart(null);
        }
    }
    public void clear() {
        Items.clear();
    }

    public boolean isEmpty() {
        return Items.isEmpty();
    }
}