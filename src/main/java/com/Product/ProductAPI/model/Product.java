package com.Product.ProductAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "products")
public class Product implements Serializable{

    @Id
    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = true)
    private String description;

    public Product() {
    }

    public Product(String sku, String designation, String description) {
        this.sku = sku;
        this.designation = designation;
        this.description = description;
    }


    public String getSku() {
        return sku;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
