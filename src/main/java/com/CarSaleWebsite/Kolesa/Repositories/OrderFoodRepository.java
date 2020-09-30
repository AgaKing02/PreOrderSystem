package com.CarSaleWebsite.Kolesa.Repositories;

import com.CarSaleWebsite.Kolesa.Models.Order;
import com.CarSaleWebsite.Kolesa.Models.OrderFood;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderFoodRepository extends CrudRepository<OrderFood, Long> {
    List<OrderFood> findAllByOrderID(Long order_ID);
}
