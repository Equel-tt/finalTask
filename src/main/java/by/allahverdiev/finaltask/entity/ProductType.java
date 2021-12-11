package by.allahverdiev.finaltask.entity;

import java.io.Serializable;

public class ProductType implements Entity, Serializable {
    private int id;
    private String name;

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

    public ProductType(int newId) {
        this.id = newId;
    }

    public ProductType(int newId, String newName) {
        this.id = newId;
        this.name = newName;
    }

    @Override
    public String toString() {
        return "Provider{"
                + "id=" + id
                + ", name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductType)) return false;
        if (this.hashCode() == o.hashCode()) return true;
        ProductType productType = (ProductType) o;
        return id == (productType.id);
    }

    @Override
    public int hashCode() {
        return id == 0 ? 0 : id * 31;
    }
}
