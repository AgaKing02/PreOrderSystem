package com.CarSaleWebsite.Kolesa.Repositories;

import com.CarSaleWebsite.Kolesa.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
}
