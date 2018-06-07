package com.guomiaomiao.learningssm.pojo;

public class Product {
    private Integer id;

    private String name;

    private Integer price;

    private Integer stock;

    public Product(Integer id, String name, Integer price, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product() {
        System.out.println("哈哈有人在偷偷使用product的构造函数");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        System.out.println("哈哈有人在偷偷使用setId()");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
        System.out.println("哈哈有人在偷偷使用setName()");
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
        System.out.println("哈哈有人在偷偷使用setPrice()");
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
        System.out.println("哈哈有人在偷偷使用setStock()");
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}