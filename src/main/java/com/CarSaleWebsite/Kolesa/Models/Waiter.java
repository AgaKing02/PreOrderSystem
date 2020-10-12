package com.CarSaleWebsite.Kolesa.Models;

import com.CarSaleWebsite.Kolesa.Models.utils.enums.Responsibilty;
import com.CarSaleWebsite.Kolesa.Models.utils.enums.Section;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "waiter_id")
public class Waiter extends Employee {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Section section;

    public Waiter(LocalDate dateAddedToWork, Responsibilty responsibilty, int salary, int experience_year, Section section) {
        super(dateAddedToWork, responsibilty, salary, experience_year);
        this.section = section;
    }

    public Waiter(String username, String password, String roles, String permissions, LocalDate dateAddedToWork, Responsibilty responsibilty, int salary, int experience_year, Section section) {
        super(username, password, roles, permissions, dateAddedToWork, responsibilty, salary, experience_year);
        this.section = section;
    }

    public Waiter(Section section) {
        this.section = section;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "Waiter{" +
                "section=" + section +
                '}';
    }
}
