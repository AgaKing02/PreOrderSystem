package com.CarSaleWebsite.Kolesa.Repositories;

import com.CarSaleWebsite.Kolesa.Models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Long> {

    List<Order> findByUserUsername(String user_username);
    Order findByIDAndUserUsername(Long ID, String user_username);
    Order findByID(Long ID);
    @Query("select count(o.ID) from Order o where o.user.username=?1 and o.status='WAITING'")
    int findCountofOrderByUsername(String username);

}
