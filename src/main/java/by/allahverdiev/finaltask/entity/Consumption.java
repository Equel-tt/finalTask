package by.allahverdiev.finaltask.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Consumption implements Entity, Serializable {
    private int id;
    private int count;
    private LocalDate date;
    private Department department;
    private Product product;

    public Consumption(int id, Product product) {
        this.id = id;
        this.product = product;
    }

    public Consumption(int id, int count, LocalDate date, Department department, Product product) {
        this.id = id;
        this.count = count;
        this.date = date;
        this.department = department;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Consumption{"
                + "id=" + id
                + ", count=" + count
                + ", date=" + date
                + ", department=" + department
                + ", product=" + product + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consumption)) return false;
        if (this.hashCode() == o.hashCode()) return true;
        Consumption consumption = (Consumption) o;
        return id == (consumption.id);
    }

    @Override
    public int hashCode() {
        return id == 0 ? 0 : id * 31;
    }
}
