package com.CarSaleWebsite.Kolesa.Repositories;

import com.CarSaleWebsite.Kolesa.Models.DiningTables;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DiningTablesRepository extends CrudRepository<DiningTables, Long> {
    DiningTables findByID(Long ID);
}
