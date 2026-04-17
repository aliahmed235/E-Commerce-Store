package com.ali.store.service;

import com.ali.store.dtos.CartDto;
import com.ali.store.dtos.CartItemDto;
import com.ali.store.dtos.UpdateCartItemRequest;
import com.ali.store.entities.Cart;
import com.ali.store.exceptions.CartNotFoundException;
import com.ali.store.exceptions.ProductNotFoundException;
import com.ali.store.mappers.CartMapper;
import com.ali.store.repositories.CartRepository;
import com.ali.store.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    public CartDto createCart() {
        var cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public CartItemDto addItemToCart(UUID cartId, Long productId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
          throw new CartNotFoundException();
        }

        var product = productRepository.findById(productId).orElse(null);
        if (product == null) {
          throw  new ProductNotFoundException();
        }

        var cartItem = cart.addItem(product);

        cartRepository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public CartItemDto updateCartItem(UUID cartId, Long productId, UpdateCartItemRequest request) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        var cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (cartItem == null) {
            throw new ProductNotFoundException();
        }

        cartItem.setQuantity(request.getQuantity());
        cartRepository.save(cart);

        return (cartMapper.toDto(cartItem));
    }
    public CartDto getCart(UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw  new CartNotFoundException();
        }
        return (cartMapper.toDto(cart));
    }

    public CartDto RemoveItem(UUID cartId, Long productId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw  new CartNotFoundException();
        }
        cart.removeItem(productId);
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }
    public CartDto clearCart(UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
           throw  new CartNotFoundException();
        }
        cart.clear();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }
}
