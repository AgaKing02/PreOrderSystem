package com.CarSaleWebsite.Kolesa.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Entity
public class Employee extends Usr {

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateAddedToWork;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Responsibilty responsibilty;

    @Column(nullable = false)
    private int salary;

    @Column(nullable = false)
    private int experience_year=0;

    public Employee(LocalDate dateAddedToWork, Responsibilty responsibilty, int salary, int experience_year) {
        this.dateAddedToWork = dateAddedToWork;
        this.responsibilty = responsibilty;
        this.salary = salary;
        this.experience_year = experience_year;
    }

    public Employee(String username, String password, String roles, String permissions, LocalDate dateAddedToWork, Responsibilty responsibilty, int salary, int experience_year) {
        super(username, password, roles, permissions);
        this.dateAddedToWork = dateAddedToWork;
        this.responsibilty = responsibilty;
        this.salary = salary;
        this.experience_year = experience_year;
    }

    public Employee() {

    }
}
