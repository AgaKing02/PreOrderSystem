package com.CarSaleWebsite.Kolesa;

import com.CarSaleWebsite.Kolesa.DTO.FoodDto;
import com.CarSaleWebsite.Kolesa.DTO.OrderProductDto;
import com.CarSaleWebsite.Kolesa.Methods.ValidationExistence;
import com.CarSaleWebsite.Kolesa.Methods.interfaces.ValidateExistence;
import com.CarSaleWebsite.Kolesa.Models.*;
import com.CarSaleWebsite.Kolesa.Models.enums.OrderStatus;
import com.CarSaleWebsite.Kolesa.Repositories.*;
import com.CarSaleWebsite.Kolesa.Services.interfaces.OrderProductService;
import com.CarSaleWebsite.Kolesa.Services.interfaces.OrderService;
import com.CarSaleWebsite.Kolesa.Services.interfaces.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class KolesaApplicationTests {
    private static Usr testUser;
    @Autowired
    private final DiningTablesRepository diningTablesRepository;
    @Autowired
    private final DiningTableTrackRepository diningTableTrackRepository;
    @Autowired
    private final FoodRepository foodRepository;
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final UsersRepository usersRepository;
    @Autowired
    private final OrderFoodRepository orderFoodRepository;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final OrderProductService orderProductService;

    KolesaApplicationTests(DiningTablesRepository diningTablesRepository, DiningTableTrackRepository diningTableTrackRepository, FoodRepository foodRepository, OrderRepository orderRepository, UsersRepository usersRepository, OrderFoodRepository orderFoodRepository, ProductService productService, OrderService orderService, OrderProductService orderProductService) {
        this.diningTablesRepository = diningTablesRepository;
        this.diningTableTrackRepository = diningTableTrackRepository;
        this.foodRepository = foodRepository;
        this.orderRepository = orderRepository;
        this.usersRepository = usersRepository;
        this.orderFoodRepository = orderFoodRepository;
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }


    @Test
    void contextLoads() {
        System.out.println("Started");
    }

    @Test
    void createUser() {
        Usr user = new Usr();
        user.setActive(1);
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword("123");
        user.setRoles("USER");
        user.setPermissions("NONE");

        testUser = usersRepository.save(user);

    }

    @Test
    void order() {
        long chair = diningTablesRepository.findByID(1L).ID;
        List<OrderProductDto> formDtos = Collections.singletonList(new OrderProductDto(new FoodDto("Coca-Cola"), 1));


        ValidateExistence validateExistence = new ValidationExistence();
        validateExistence.validateExistence(formDtos, productService);

        Order order = new Order();
        order.setStatus(OrderStatus.WAITING.name());
        order.setUser(usersRepository.findByUsername(testUser.getUsername()));
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
        if (chair > 0) {
            DiningTables diningTables = diningTablesRepository.findByID((long) chair);
            diningTableTrackRepository.save(new DiningTableTrack(diningTables, order));
        }

    }

}
