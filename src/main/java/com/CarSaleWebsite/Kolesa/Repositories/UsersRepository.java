package com.CarSaleWebsite.Kolesa.Repositories;

import com.CarSaleWebsite.Kolesa.Models.Usr;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Usr,Long> {
    Usr findUsersByUsername(String username);
}
