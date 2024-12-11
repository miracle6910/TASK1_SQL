package jm.task.core.jdbc.model;

import javax.persistence.Column;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private Byte age;



    public User(String name, String lastName, byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public User() {
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public byte getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Пользователь{" +
                "Номер=" + id +
                ", Имя='" + name + '\'' +
                ", Фамилия='" + lastName + '\'' +
                ", Возраст=" + age +
                '}';
    }
}