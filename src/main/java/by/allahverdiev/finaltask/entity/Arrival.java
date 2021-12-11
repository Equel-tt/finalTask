package by.allahverdiev.finaltask.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Arrival implements Entity, Serializable {
    private String document;
    private double count;
    private LocalDate date;
    private Product product;
    private double price;
    private User user;

    public Arrival() {
    }

    public Arrival(String document) {
        this.document = document;
    }

    public Arrival(String document, double count, LocalDate date, double price, User user) {
        this.document = document;
        this.count = count;
        this.date = date;
        this.price = price;
        this.user = user;
    }

    public Arrival(String document, double count, LocalDate date, Product product, double price, User user) {
        this.document = document;
        this.count = count;
        this.date = date;
        this.product = product;
        this.price = price;
        this.user = user;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return this.document + " " +
                this.product.getId() + " " +
                this.date + " " +
                this.count + " " +
                this.price + " " +
                this.user.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Arrival)) return false;
        if (this.hashCode() == o.hashCode()) return true;
        Arrival arrival = (Arrival) o;
        return document == (arrival.document) && (product == arrival.product);
    }

    @Override
    public int hashCode() {
        int result = document == null ? 0 : document.hashCode();
        result = 31 * result + product.getId();
        return result;
    }
}
