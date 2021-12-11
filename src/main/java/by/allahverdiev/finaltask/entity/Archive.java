package by.allahverdiev.finaltask.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Archive implements Entity, Serializable {
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

    @Override
    public String toString() {
        return "Archive{"
                + "date=" + date
                + ", product=" + product
                + ", count=" + count + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Archive)) return false;
        if (this.hashCode() == o.hashCode()) return true;
        Archive archive = (Archive) o;
        return date == (archive.date) && (product == archive.product);
    }

    @Override
    public int hashCode() {
        int result = date == null ? 0 : date.hashCode();
        result = 31 * result + product.getId();
        return result;
    }
}
