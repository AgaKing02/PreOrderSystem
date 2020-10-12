package com.CarSaleWebsite.Kolesa.Models;

import com.CarSaleWebsite.Kolesa.Models.utils.enums.Responsibilty;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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

    public LocalDate getDateAddedToWork() {
        return dateAddedToWork;
    }

    public void setDateAddedToWork(LocalDate dateAddedToWork) {
        this.dateAddedToWork = dateAddedToWork;
    }

    public Responsibilty getResponsibilty() {
        return responsibilty;
    }

    public void setResponsibilty(Responsibilty responsibilty) {
        this.responsibilty = responsibilty;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getExperience_year() {
        return experience_year;
    }

    public void setExperience_year(int experience_year) {
        this.experience_year = experience_year;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "dateAddedToWork=" + dateAddedToWork +
                ", responsibilty=" + responsibilty +
                ", salary=" + salary +
                ", experience_year=" + experience_year +
                '}';
    }
}
