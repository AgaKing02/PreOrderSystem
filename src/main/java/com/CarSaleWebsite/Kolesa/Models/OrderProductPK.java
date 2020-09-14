package com.CarSaleWebsite.Kolesa.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderProductPK implements Serializable {

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Food food;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProductPK)) return false;
        OrderProductPK that = (OrderProductPK) o;
        return Objects.equals(getOrder(), that.getOrder()) &&
                Objects.equals(getFood(), that.getFood());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getFood());
    }

}