package by.allahverdiev.finaltask.entity;

public class User implements Entity {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private int role;

    public User(int newId) {
        this.id = newId;
    }

    public User(int newId, String newName) {
        this.id = newId;
        this.name = newName;
    }

    public User(int newId, String newName, String newSurname) {
        this.id = newId;
        this.name = newName;
        this.surname = newSurname;
    }

    public User(int newId, String newName, String newSurname, String newPatronymic) {
        this.id = newId;
        this.name = newName;
        this.surname = newSurname;
        this.patronymic = newPatronymic;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + " " + this.patronymic;
    }
}
