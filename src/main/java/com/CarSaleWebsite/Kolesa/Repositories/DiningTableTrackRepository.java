package com.CarSaleWebsite.Kolesa.Repositories;

import com.CarSaleWebsite.Kolesa.Models.utils.DiningTableTrack;
import com.CarSaleWebsite.Kolesa.Models.utils.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiningTableTrackRepository extends CrudRepository<DiningTableTrack,Long> {
    @Query("select count(u.orderid) from DiningTableTrack u where u.diningTablesid=?1")
    int CountofOrderByTableID(Long id);
    void deleteByOrderid(Order orderid);
    DiningTableTrack findByOrderid(Order orderid);
}