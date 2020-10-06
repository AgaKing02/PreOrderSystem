package com.CarSaleWebsite.Kolesa.Controllers;

import com.CarSaleWebsite.Kolesa.DTO.AjaxResponseBody;
import com.CarSaleWebsite.Kolesa.DTO.OrderProductDto;
import com.CarSaleWebsite.Kolesa.Functions.ValidationExistence;
import com.CarSaleWebsite.Kolesa.Functions.interfaces.ValidateExistence;
import com.CarSaleWebsite.Kolesa.Models.Order;
import com.CarSaleWebsite.Kolesa.Models.OrderFood;
import com.CarSaleWebsite.Kolesa.Models.OrderStatus;
import com.CarSaleWebsite.Kolesa.Repositories.OrderFoodRepository;
import com.CarSaleWebsite.Kolesa.Repositories.OrderRepository;
import com.CarSaleWebsite.Kolesa.Repositories.UsersRepository;
import com.CarSaleWebsite.Kolesa.Services.OrderProductServiceImpl;
import com.CarSaleWebsite.Kolesa.Services.OrderServiceImpl;
import com.CarSaleWebsite.Kolesa.Services.interfaces.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestContoller {
    private final OrderProductServiceImpl orderProductService;
    private final ProductService productService;
    private final UsersRepository usersRepository;
    private final OrderServiceImpl orderService;
    private final OrderFoodRepository orderFoodRepository;
    private final OrderRepository orderRepository;

    public TestContoller(OrderProductServiceImpl orderProductService, ProductService productService, UsersRepository usersRepository, OrderServiceImpl orderService, OrderFoodRepository orderFoodRepository, OrderRepository orderRepository) {
        this.orderProductService = orderProductService;
        this.productService = productService;
        this.usersRepository = usersRepository;
        this.orderService = orderService;
        this.orderFoodRepository = orderFoodRepository;
        this.orderRepository = orderRepository;
    }


    @PostMapping("/api/test")
    public ResponseEntity<?> create(@RequestBody PurchaseController.OrderForm form, Errors errors, Principal principal) throws JsonProcessingException {
        AjaxResponseBody result = new AjaxResponseBody();

        if (errors.hasErrors()) {

            result.setMessage(errors.getAllErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }
        if(orderRepository.findCountofOrderByUsername(principal.getName())>=2){

            return  ResponseEntity.badRequest().body("Firstly pay the waiting orders");
        }

            if(principal.getName().isEmpty()){
           return ResponseEntity.badRequest().body("User not found");
        }
        List<OrderProductDto> formDtos = form.getProductOrders();

        ValidateExistence validateExistence=new ValidationExistence();
        validateExistence.validateExistence(formDtos,productService);

        Order order = new Order();
        order.setStatus(OrderStatus.WAITING.name());
        order.setUser(usersRepository.findByUsername(principal.getName()));
        order = this.orderService.create(order);

        List<OrderFood> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
            orderProducts
                    .add(orderProductService
                            .create(new OrderFood(
                                    order, productService.getProduct(
                                    dto.getProduct().getName()), dto.getQuantity())));
        }
        order.setOrderProducts(orderProducts);
        this.orderService.update(order);

        if(!formDtos.isEmpty()){
            result.setMessage("success");
        }else{
            result.setMessage("error");
            return ResponseEntity.badRequest().body(result);
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(formDtos);
        result.setResult(jsonString);

        return ResponseEntity.ok(result);


           }



}
