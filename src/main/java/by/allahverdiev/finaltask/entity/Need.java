package by.allahverdiev.finaltask.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Need implements Entity, Serializable {
    private LocalDate month;
    private Product product;
    private int count;
    private Department department;

    public Need(LocalDate month, Product product) {
        this.month = month;
        this.product = product;
    }

    public Need(LocalDate month, Product product, int count, Department department) {
        this.month = month;
        this.product = product;
        this.count = count;
        this.department = department;
    }

    public LocalDate getMonth() {
        return month;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Need)) return false;
        if (this.hashCode() == o.hashCode()) return true;
        Need need = (Need) o;
        return month == (need.month) && (product == need.product);
    }

    @Override
    public int hashCode() {
        int result = month == null ? 0 : month.hashCode();
        result = 31 * result + product.getId();
        return result;
    }

    @Override
    public String toString() {
        return "Need{ "
                + "month=" + month
                + ", product=" + product
                + ", count=" + count
                + ", department=" + department + '}';
    }
}
