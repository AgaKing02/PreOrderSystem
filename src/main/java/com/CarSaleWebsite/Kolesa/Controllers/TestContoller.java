package com.CarSaleWebsite.Kolesa.Controllers;

import com.CarSaleWebsite.Kolesa.DTO.AjaxResponseBody;
import com.CarSaleWebsite.Kolesa.DTO.FoodDto;
import com.CarSaleWebsite.Kolesa.DTO.OrderProductDto;
import com.CarSaleWebsite.Kolesa.Repositories.UsersRepository;
import com.CarSaleWebsite.Kolesa.Services.OrderProductServiceImpl;
import com.CarSaleWebsite.Kolesa.Services.OrderServiceImpl;
import com.CarSaleWebsite.Kolesa.Services.interfaces.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestContoller {
    private final OrderProductServiceImpl orderProductService;
    private final ProductService productService;
    private final UsersRepository usersRepository;
    private final OrderServiceImpl orderService;

    public TestContoller(OrderProductServiceImpl orderProductService, ProductService productService, UsersRepository usersRepository, OrderServiceImpl orderService) {
        this.orderProductService = orderProductService;
        this.productService = productService;
        this.usersRepository = usersRepository;
        this.orderService = orderService;
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
    public ResponseEntity<?> create(@RequestBody PurchaseController.OrderForm form, Errors errors) throws JsonProcessingException {
        AjaxResponseBody result = new AjaxResponseBody();

        if (errors.hasErrors()) {

            result.setMessage(errors.getAllErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }
        List<OrderProductDto> formDtos = form.getProductOrders();
//        List<OrderProduct> orderProducts = new ArrayList<>();
//
//        Order order = new Order();
//        order.setStatus(OrderStatus.PAID.name());
//        order.setUser(usersRepository.findByUsername("AgaKing"));
//        order = this.orderService.create(order);
//
//        for (OrderProductDto dto : formDtos) {
//            OrderProduct product = new OrderProduct(order, productService.getProduct(dto.getProduct().getName()), dto.getQuantity());
//            OrderProduct food=orderProductService.create(product);
//        }
        if(!formDtos.isEmpty()){
            result.setMessage("success");
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(formDtos);
        result.setResult(jsonString);

        return ResponseEntity.ok(result);


           }
}
