package com.CarSaleWebsite.Kolesa.Models.Patterns;

import com.CarSaleWebsite.Kolesa.Models.utils.Food;

public class FoodBuilderBuilderWithDiscountDecorator implements FoodBuilderBuilder {
    private static final double MIN_DISCOUNT = 0.0;
    private static final double MAX_DISCOUNT = 1.0;

    private final FoodBuilderBuilder inner;
    private final double discount;

    public FoodBuilderBuilderWithDiscountDecorator(FoodBuilderBuilder inner, double discount) {
        if (discount < MIN_DISCOUNT) {
            throw new IllegalArgumentException("Discount can't be less than " + MIN_DISCOUNT);
        }

        if (discount > MAX_DISCOUNT) {
            throw new IllegalArgumentException("Discount can't be greater than " + MAX_DISCOUNT);
        }

        this.inner = inner;
        this.discount = discount;
    }


    @Override
    public FoodBuilderBuilder withPrice(double price) {
        return inner.withPrice(price - (price * discount));
    }

    @Override
    public FoodBuilderBuilder withName(String name) {
        return inner.withName(name);
    }

    @Override
    public FoodBuilderBuilder withCategory(String name) {
        return inner.withCategory(name);
    }

    @Override
    public FoodBuilderBuilder withImageUrl(String imageUrl) {
        return inner.withImageUrl(imageUrl);
    }

    @Override
    public FoodBuilderBuilder withDescription(String description) {
        return inner.withDescription(description);
    }

    @Override
    public Food build() {
        return inner.build();
    }
}
