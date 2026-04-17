
package com.ali.store.controllers;

import com.ali.store.dtos.AddItemToCartRequest;
import com.ali.store.dtos.CartDto;
import com.ali.store.dtos.CartItemDto;
import com.ali.store.dtos.UpdateCartItemRequest;
import com.ali.store.exceptions.CartNotFoundException;
import com.ali.store.exceptions.ProductNotFoundException;
import com.ali.store.mappers.CartMapper;
import com.ali.store.repositories.CartRepository;
import com.ali.store.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.ali.store.service.CartService;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<CartDto> createCart(UriComponentsBuilder uriBuilder) {
        var cartDto =cartService.createCart();
        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();
        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(@PathVariable("cartId") UUID cartId,
                                                 @RequestBody AddItemToCartRequest request) {

        var cartItemDto = cartService.addItemToCart(cartId, request.getProductId());

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable("cartId") UUID cartId) {
    var cart = cartService.getCart(cartId);
    return ResponseEntity.ok(cart);
    }


    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateItemQuantity(@PathVariable("cartId") UUID cartId,
                                                          @PathVariable("productId") Long productId,
                                                          @Valid @RequestBody UpdateCartItemRequest request) {
       var cart = cartService.updateCartItem(cartId, productId, request);
         return ResponseEntity.ok(cart);
    }

        @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> removeItem(@PathVariable("cartId") UUID cartId,
                                        @PathVariable("productId") Long productId) {
         var cart = cartService.RemoveItem(cartId, productId);
            return ResponseEntity.ok(cart);
        }

        @DeleteMapping("/{cartId}/items")
        public ResponseEntity<Void>clearCart(@PathVariable("cartId") UUID cartId) {
            cartService.clearCart(cartId);
            return ResponseEntity.noContent().build();
        }

        @ExceptionHandler(CartNotFoundException.class)
        public ResponseEntity<Map<String , String>>handleCartNotFoundException() {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "Cart not found")
            );
        }

        @ExceptionHandler(ProductNotFoundException.class)
        public ResponseEntity<Map<String , String>>handleProductNotFoundException() {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", "Product not found")
        );
         }
}
