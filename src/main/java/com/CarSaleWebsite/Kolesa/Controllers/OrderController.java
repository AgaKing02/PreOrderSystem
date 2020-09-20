package com.CarSaleWebsite.Kolesa.Controllers;

import com.CarSaleWebsite.Kolesa.Models.Order;
import com.CarSaleWebsite.Kolesa.Services.interfaces.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("api/orders")
    public String list(Principal principal, Model model) {
        Iterable<Order> orders = orderService.getMyOrder(principal.getName());
        if (orders == null) {
            return "redirect:/catalog";
        } else {
            model.addAttribute("orders", orders);
        }
        return "order-page";

    }
}
