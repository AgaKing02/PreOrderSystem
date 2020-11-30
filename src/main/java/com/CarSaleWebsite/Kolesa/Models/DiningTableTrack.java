package com.CarSaleWebsite.Kolesa.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class DiningTableTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long track_id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    public DiningTables diningTablesid;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    public Order orderid;

    public DiningTableTrack(DiningTables diningTables_id, Order order_id) {
        this.diningTablesid = diningTables_id;
        this.orderid = order_id;
    }

    public DiningTableTrack() {

    }

    public Long getTrack_id() {
        return track_id;
    }


    public DiningTables getDiningTablesid() {
        return diningTablesid;
    }

    public void setDiningTablesid(DiningTables diningTablesid) {
        this.diningTablesid = diningTablesid;
    }

    public Order getOrderid() {
        return orderid;
    }

    public void setOrderid(Order orderid) {
        this.orderid = orderid;
    }
}
