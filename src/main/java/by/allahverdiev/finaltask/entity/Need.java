package by.allahverdiev.finaltask.entity;

import java.time.LocalDate;

public class Need implements Entity {
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
}
