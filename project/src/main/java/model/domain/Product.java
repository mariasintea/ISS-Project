package model.domain;

import java.io.Serializable;

/**
 * Product
 * - id: int; product's id
 * - name: string; product's name
 * - price: double; product's price
 * - availableQuantity: int; number of available products
 */
public class Product implements Serializable {
    int id;
    String name;
    double price;
    int availableQuantity;


    public Product(int id, String name, double price, int availableQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public boolean extractFromQuantity(double quantity){
        if (this.availableQuantity >= quantity){
            this.availableQuantity -= quantity;
            return true;
        }
        return false;
    }
}
