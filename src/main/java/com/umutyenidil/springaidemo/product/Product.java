package com.umutyenidil.springaidemo.product;

import java.math.BigDecimal;

public record Product(
        String name,
        String category,
        BigDecimal price,
        BigDecimal discountPrice
) {
}
