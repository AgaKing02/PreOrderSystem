package com.CarSaleWebsite.Kolesa.Repositories;

import com.CarSaleWebsite.Kolesa.Models.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends CrudRepository<Food,Long> {
     Food findFoodByName(String name);
     Food findFoodByCategoryAndName(String category,String name);
     Boolean existsByCategoryAndName(String category,String name);
     Boolean existsByName(String name);
     Boolean existsByID(long id);
     Food findByID(Long id);


}
