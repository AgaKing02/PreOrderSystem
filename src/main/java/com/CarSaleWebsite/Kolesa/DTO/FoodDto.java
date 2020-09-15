package com.CarSaleWebsite.Kolesa.DTO;

import com.CarSaleWebsite.Kolesa.Models.Food;

public class FoodDto {
    public long ID;

    public FoodDto(long ID) {
        this.ID = ID;
    }
    public FoodDto(){}

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
    public FoodDto(Food food){
        this.ID=food.getID();
    }


}
