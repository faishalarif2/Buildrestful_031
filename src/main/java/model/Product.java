/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NITRO
 */
public class Product {
    private String id;
    private String name;
    private int price;
    private double disc;
    private int total;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDisc(double d) {
        return disc;
    }

    public void setDisc(double disc) {
        this.disc = disc;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal() {
        this.total = (int) (price - price * disc);
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
