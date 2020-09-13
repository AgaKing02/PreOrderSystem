package com.CarSaleWebsite.Kolesa.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Objects;

@Entity
public class OrderProduct {

    @EmbeddedId
    @JsonIgnore
    private OrderProductPK pk;

    @Column(nullable = false)
    private Integer quantity;


    public OrderProduct(Order order, Food product, Integer quantity) {
        pk = new OrderProductPK();
        pk.setOrder(order);
        pk.setFood(product);
        this.quantity = quantity;
    }

    public OrderProduct() {

    }

    @Transient
    public Food getProduct() {
        return this.pk.getFood();
    }

    @Transient
    public long getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }

    public OrderProductPK getPk() {
        return pk;
    }

    public void setPk(OrderProductPK pk) {
        this.pk = pk;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProduct)) return false;
        OrderProduct that = (OrderProduct) o;
        return Objects.equals(getPk(), that.getPk()) &&
                Objects.equals(getQuantity(), that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPk(), getQuantity());
    }
}