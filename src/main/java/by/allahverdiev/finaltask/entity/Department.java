package by.allahverdiev.finaltask.entity;

import java.io.Serializable;

public class Department implements Entity, Serializable {
    private int id;
    private String name;

    public Department() {
    }

    public Department(int id) {
        this.id = id;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Provider{"
                + "id=" + id
                + ", name='" + name + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        if (this.hashCode() == o.hashCode()) return true;
        Department department = (Department) o;
        return id == (department.id);
    }

    @Override
    public int hashCode() {
        return id == 0 ? 0 : id * 31;
    }
}
