package org.lab;


import org.lab.entities.Person;
import org.lab.serializer.JsonSerializer;

public class Main {
    public static void main(String[] args) throws IllegalAccessException{
        Person person = new Person();
        JsonSerializer serializer = new JsonSerializer(person);
        System.out.println(serializer.serialize());
    }
}
