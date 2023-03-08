package com.june.learn;

public class ConstructorArg {
    private String name;
    private String age;

    public ConstructorArg(String name, String age) {
        this.name = name;
        this.age = age;
    }
//
//    public ConstructorArg() {
//
//    }

    public void sayHello(){
        System.out.println("hello i am "+this.name + age +"year old!!!");
    }
}
