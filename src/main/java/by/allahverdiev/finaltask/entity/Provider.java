package by.allahverdiev.finaltask.entity;

public class Provider implements Entity {
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

    public Provider(int newId) {
        this.id = newId;
    }

    public Provider(int newId, String newName) {
        this.id = newId;
        this.name = newName;
    }

    @Override
    public String toString() {
//        return this.name;
        return String.valueOf(this.id);
    }
}
