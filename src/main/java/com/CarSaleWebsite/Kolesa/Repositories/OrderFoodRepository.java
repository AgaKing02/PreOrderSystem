package com.CarSaleWebsite.Kolesa.Repositories;

import com.CarSaleWebsite.Kolesa.Models.Order;
import com.CarSaleWebsite.Kolesa.Models.OrderFood;
import org.springframework.data.repository.CrudRepository;

public interface OrderFoodRepository extends CrudRepository<OrderFood, Long> {
}
