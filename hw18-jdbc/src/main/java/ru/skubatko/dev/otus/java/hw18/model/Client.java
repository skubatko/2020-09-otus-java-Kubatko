package ru.skubatko.dev.otus.java.hw18.model;

import ru.skubatko.dev.otus.java.hw18.annotaion.Id;

public class Client {

    @Id
    private final long id;
    private final String name;
    private final int age;

    public Client(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Client{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", age=" + age +
                       '}';
    }
}
