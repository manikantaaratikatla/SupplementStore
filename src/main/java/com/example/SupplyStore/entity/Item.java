package com.example.SupplyStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "items_details")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemid;

    private String itemname;
    private double price;
    private String imageurl;
    private double weight;

    @Column(name = "avail_quantity")
    private int availQuantity;

    // Getters and Setters
}
