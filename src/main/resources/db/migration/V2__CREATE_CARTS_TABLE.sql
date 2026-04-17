CREATE TABLE carts (
                       id BINARY(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID())),
                       date_created DATE NOT NULL DEFAULT (CURDATE()),
                       PRIMARY KEY (id)
);

CREATE TABLE cart_items (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            cart_id BINARY(16) NOT NULL,
                            product_id BIGINT NOT NULL,
                            quantity INT NOT NULL DEFAULT 1,

                            PRIMARY KEY (id),
                            UNIQUE (cart_id, product_id),

                            CONSTRAINT fk_cart_items_cart
                                FOREIGN KEY (cart_id) REFERENCES carts(id)
                                    ON DELETE CASCADE,

                            CONSTRAINT fk_cart_items_product
                                FOREIGN KEY (product_id) REFERENCES products(id)
                                    ON DELETE CASCADE
);
