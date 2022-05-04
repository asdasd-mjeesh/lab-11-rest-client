package com.asdasd.mjeesh.client.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {
    private Long id;
    private String title;
    private BigDecimal cost;
    private LocalDate shelfLife;
    private Producer producer;

    public Product() {  }

    public Product(Long id, String title, BigDecimal cost, LocalDate shelfLife, Producer producer) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.shelfLife = shelfLife;
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", cost=" + cost +
               ", shelfLife=" + shelfLife +
               ", producer=" + producer +
               '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDate getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(LocalDate shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
