package com.CarSaleWebsite.Kolesa.Controllers;

import com.CarSaleWebsite.Kolesa.Models.DiningTableTrack;
import com.CarSaleWebsite.Kolesa.Models.Order;
import com.CarSaleWebsite.Kolesa.Models.OrderFood;
import com.CarSaleWebsite.Kolesa.Repositories.DiningTableTrackRepository;
import com.CarSaleWebsite.Kolesa.Repositories.OrderFoodRepository;
import com.CarSaleWebsite.Kolesa.Repositories.OrderRepository;
import com.CarSaleWebsite.Kolesa.Services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;


@Controller
public class OrderController {
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final OrderFoodRepository orderFoodRepository;
    @Autowired
    private final DiningTableTrackRepository diningTableTrackRepository;

    public OrderController(OrderService orderService, OrderRepository orderRepository, OrderFoodRepository orderFoodRepository, DiningTableTrackRepository diningTableTrackRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.orderFoodRepository = orderFoodRepository;
        this.diningTableTrackRepository = diningTableTrackRepository;
    }

    @GetMapping("api/orders")
    public String list(Principal principal, Model model, @RequestParam(name = "last", required = false, defaultValue = "-1") long order) {
        Iterable<Order> orders = orderService.getMyOrder(principal.getName());
        if (orders == null) {
            return "redirect:/catalog";
        } else {
            model.addAttribute("orders", orders);
        }
        return "order-page";

    }

    @PostMapping("/api/orders/remove/{order}")
    public String deleteOrder(Principal principal, @PathVariable(name = "order") Long id) {
        Order deletable = orderRepository.findByIDAndUserUsername(id, principal.getName());

        if (deletable.getStatus().equals("WAITING") || deletable.getStatus().equals("WITHCASH") || deletable.getStatus().equals("WITHWAITER")) {
            List<OrderFood> orderFoods = orderFoodRepository.findAllByOrderID(id);

            DiningTableTrack diningTableTrack = diningTableTrackRepository.findByOrderid(deletable);
            if (diningTableTrack != null) {
                diningTableTrackRepository.delete(diningTableTrack);
            }
            orderFoodRepository.deleteAll(orderFoods);
            orderRepository.delete(deletable);


        }

        return "redirect:/api/orders";
    }

}
