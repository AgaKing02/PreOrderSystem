package com.CarSaleWebsite.Kolesa.Models.Patterns;

import com.CarSaleWebsite.Kolesa.Models.utils.Food;

public class FoodBuilderBuilderImpl implements FoodBuilderBuilder {
    private String category;
    private double price;
    private String name;
    private String description;
    private String imageUrl;
    private String size;

    @Override
    public FoodBuilderBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public FoodBuilderBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public FoodBuilderBuilder withCategory(String name) {
        this.category=name;
        return this;
    }

    @Override
    public FoodBuilderBuilder withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Override
    public FoodBuilderBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public FoodBuilderBuilder withSize(String size) {
        this.size = size;
        return this;
    }

    @Override
    public Food build() {
        return new Food(name, description, (long) price,size,imageUrl,category );
    }
}
