package com.june.myspring.pojo;

public class User {
    private String name;
    private Integer age;
    private Integer sex;
    private String description;

    public User(String name, Integer age, Integer sex, String description) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", description='" + description + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSex() {
        return sex;
    }

    public String getDescription() {
        return description;
    }
}
