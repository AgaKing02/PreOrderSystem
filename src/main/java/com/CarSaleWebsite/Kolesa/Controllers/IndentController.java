package com.CarSaleWebsite.Kolesa.Controllers;

import com.CarSaleWebsite.Kolesa.Models.utils.Order;
import com.CarSaleWebsite.Kolesa.Models.utils.enums.OrderStatus;
import com.CarSaleWebsite.Kolesa.Repositories.OrderFoodRepository;
import com.CarSaleWebsite.Kolesa.Repositories.OrderRepository;
import com.CarSaleWebsite.Kolesa.Services.interfaces.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/indent")
public class IndentController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderFoodRepository orderFoodRepository;

    public IndentController(OrderService orderService, OrderRepository orderRepository, OrderFoodRepository orderFoodRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.orderFoodRepository = orderFoodRepository;
    }
    @RequestMapping(value = "/general",method = RequestMethod.GET)
    public String getGeneralOrders(Model model){
        List<Order> orderList=orderRepository.findGeneralOrders();
        model.addAttribute("orders",orderList);
        return "indent-page";
    }
}
