package com.CarSaleWebsite.Kolesa.Repositories;

import com.CarSaleWebsite.Kolesa.Models.utils.DiningTableTrack;
import com.CarSaleWebsite.Kolesa.Models.utils.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiningTableTrackRepository extends CrudRepository<DiningTableTrack,Long> {
    @Query("select count(u.order_id) from DiningTableTrack u where u.diningTables_id=?1")
    int CountofOrderByTableID(Long id);
   @Query("delete from DiningTableTrack d where d.order_id=?1")
    void deleteByOrder(Order order);
}