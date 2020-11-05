package com.CarSaleWebsite.Kolesa.Models.Patterns;

import com.CarSaleWebsite.Kolesa.Models.utils.Food;

public interface FoodBuilderBuilder {


    FoodBuilderBuilder withPrice(double price);
    FoodBuilderBuilder withName(String name);
    FoodBuilderBuilder withCategory(String name);
    FoodBuilderBuilder withImageUrl(String imageUrl);
    FoodBuilderBuilder withDescription(String description);
    FoodBuilderBuilder withSize(String size);

    Food build();
}
