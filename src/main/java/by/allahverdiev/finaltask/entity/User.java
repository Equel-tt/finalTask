package by.allahverdiev.finaltask.entity;

import java.io.Serializable;

public class User implements Entity, Serializable {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String description;
    private int role;

    public User() {
    }

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

    public User(int newId, String newName, String newSurname, String newPatronymic, int newRole) {
        this.id = newId;
        this.name = newName;
        this.surname = newSurname;
        this.patronymic = newPatronymic;
        this.role = newRole;
    }

    public User(int newId, String newName, String newSurname, String newPatronymic, int newRole, String newDescription) {
        this.id = newId;
        this.name = newName;
        this.surname = newSurname;
        this.patronymic = newPatronymic;
        this.role = newRole;
        this.description = newDescription;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.surname + " " + this.name + " " + this.patronymic + " / " + this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (this.hashCode() == o.hashCode()) return true;
        User user = (User) o;
        return id == (user.id);
    }

    @Override
    public int hashCode() {
        return id == 0 ? 0 : id * 31;
    }

}
