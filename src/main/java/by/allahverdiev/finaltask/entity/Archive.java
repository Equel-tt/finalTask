package by.allahverdiev.finaltask.entity;

import java.time.LocalDate;

public class Archive implements Entity {
    private LocalDate date;
    private Product product;
    private int count;

    public Archive(LocalDate date, Product product) {
        this.date = date;
        this.product = product;
    }

    public Archive(LocalDate date, Product product, int count) {
        this.date = date;
        this.product = product;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
