package com.CarSaleWebsite.Kolesa.Controllers;

import com.CarSaleWebsite.Kolesa.DTO.AjaxResponseBody;
import com.CarSaleWebsite.Kolesa.DTO.FoodDto;
import com.CarSaleWebsite.Kolesa.DTO.OrderProductDto;
import com.CarSaleWebsite.Kolesa.Exceptions.ResourceNotFoundException;
import com.CarSaleWebsite.Kolesa.Models.Order;
import com.CarSaleWebsite.Kolesa.Models.OrderFood;
import com.CarSaleWebsite.Kolesa.Models.OrderStatus;
import com.CarSaleWebsite.Kolesa.Repositories.OrderFoodRepository;
import com.CarSaleWebsite.Kolesa.Repositories.UsersRepository;
import com.CarSaleWebsite.Kolesa.Services.OrderProductServiceImpl;
import com.CarSaleWebsite.Kolesa.Services.OrderServiceImpl;
import com.CarSaleWebsite.Kolesa.Services.interfaces.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class TestContoller {
    private final OrderProductServiceImpl orderProductService;
    private final ProductService productService;
    private final UsersRepository usersRepository;
    private final OrderServiceImpl orderService;
    private final OrderFoodRepository orderFoodRepository;

    public TestContoller(OrderProductServiceImpl orderProductService, ProductService productService, UsersRepository usersRepository, OrderServiceImpl orderService, OrderFoodRepository orderFoodRepository) {
        this.orderProductService = orderProductService;
        this.productService = productService;
        this.usersRepository = usersRepository;
        this.orderService = orderService;
        this.orderFoodRepository = orderFoodRepository;
    }


    @GetMapping("/test")
    public String test() throws JsonProcessingException {

        OrderProductDto dto = new OrderProductDto();
        int quantity = 3;
        FoodDto food = new FoodDto();
        food.setName("Whopper");
        dto.setProduct(food);
        dto.setQuantity(quantity);


        OrderProductDto dto2 = new OrderProductDto();
        int quantity2 = 5;
        FoodDto food2 = new FoodDto();
        food2.setName("Coca-Cola");
        dto2.setProduct(food2);
        dto2.setQuantity(quantity2);


        List<OrderProductDto> list = new LinkedList<>();
        list.add(dto);
        list.add(dto2);


        PurchaseController.OrderForm form = new PurchaseController.OrderForm();
        form.setProductOrders(list);


        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(form);

        return jsonString;
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
        if(principal.getName().isEmpty()){
           return ResponseEntity.badRequest().body("User not found");
        }
        List<OrderProductDto> formDtos = form.getProductOrders();
        validateProductsExistence(formDtos);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
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
        orderProducts.forEach(orderFoodRepository::save);
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
    private void validateProductsExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(productService.getProduct(op.getProduct().getName()))).collect(Collectors.toList());




        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }
}
