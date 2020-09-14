package com.CarSaleWebsite.Kolesa.Repositories;

import com.CarSaleWebsite.Kolesa.Models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long> {
}
