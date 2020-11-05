package com.CarSaleWebsite.Kolesa.Models.Patterns;

import com.CarSaleWebsite.Kolesa.Models.utils.Food;

public interface ItemBuilder {


    ItemBuilder withPrice(double price);
    ItemBuilder withName(String name);
    ItemBuilder withCategory(String name);
    ItemBuilder withImageUrl(String imageUrl);
    ItemBuilder withDescription(String description);

    Food build();
}
