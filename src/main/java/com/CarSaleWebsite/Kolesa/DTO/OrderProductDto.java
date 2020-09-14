package com.CarSaleWebsite.Kolesa.DTO;

import com.CarSaleWebsite.Kolesa.Models.Food;

public class OrderProductDto {

    private Food product;
    private Integer quantity;

    public Food getProduct() {
        return product;
    }

    public void setProduct(Food product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}