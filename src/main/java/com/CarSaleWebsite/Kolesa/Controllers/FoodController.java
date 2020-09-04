package com.CarSaleWebsite.Kolesa.Controllers;

import com.CarSaleWebsite.Kolesa.Models.Food;
import com.CarSaleWebsite.Kolesa.Repositories.FoodRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FoodController {
    private final FoodRepository foodRepository;

    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
    @GetMapping("/order/{id}")
    public String qrCodeGenerator(Model model, @PathVariable("id")Long id){
        if(!foodRepository.existsByID(id)){
            return "redirect:/";
        }
        Food food=foodRepository.findByID(id);
        model.addAttribute("food",food.toString());
        return "QrCodeGen";
    }

}
