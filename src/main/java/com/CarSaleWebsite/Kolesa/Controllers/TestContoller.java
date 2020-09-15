package com.CarSaleWebsite.Kolesa.Controllers;

import com.CarSaleWebsite.Kolesa.DTO.FoodDto;
import com.CarSaleWebsite.Kolesa.DTO.OrderProductDto;
import com.CarSaleWebsite.Kolesa.Models.Food;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
public class TestContoller {
    @GetMapping("/test")
    public String test() throws JsonProcessingException {

        OrderProductDto dto = new OrderProductDto();
        int quantity = 3;
        FoodDto food = new FoodDto();
        food.setID(1);
        dto.setProduct(food);
        dto.setQuantity(quantity);


        OrderProductDto dto2 = new OrderProductDto();
        int quantity2 = 5;
        FoodDto food2 = new FoodDto();
        food2.setID(4);
        dto2.setProduct(food2);
        dto2.setQuantity(quantity2);
        

        List<OrderProductDto> list=new LinkedList<>();
        list.add(dto);
        list.add(dto2);


        PurchaseController.OrderForm form=new PurchaseController.OrderForm();
        form.setProductOrders(list);





        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(form);

        return jsonString;
    }
}
