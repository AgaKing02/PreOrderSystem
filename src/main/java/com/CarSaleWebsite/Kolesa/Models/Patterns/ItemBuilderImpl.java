package com.CarSaleWebsite.Kolesa.Models.Patterns;

import com.CarSaleWebsite.Kolesa.Models.utils.Food;

public class ItemBuilderImpl implements ItemBuilder {
    private String category;
    private double price;
    private String name;
    private String description;
    private String imageUrl;
    private String size="M";





    @Override
    public ItemBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ItemBuilder withCategory(String name) {
        this.category=name;
        return this;
    }

    @Override
    public ItemBuilder withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Override
    public ItemBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public Food build() {
        return new Food(name, description, (long) price,size,imageUrl,category );
    }
}
