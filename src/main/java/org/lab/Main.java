package org.lab;


import org.lab.entities.Dog;
import org.lab.entities.Person;
import org.lab.serializer.JsonSerializer;

public class Main {
    public static void main(String[] args) throws IllegalAccessException{
        Dog dog = new Dog();
        dog.setName("Bobby");
        dog.setWeight(20.5);

        Dog dog1 = new Dog();
        dog1.setName("Bebrik");
        dog1.setWeight(13.37);

        Person person = new Person();
        person.setName("Kirill");
        person.setAge(19);
        person.setDog(dog);
        person.setArr(new Dog[]{dog, dog1});

        JsonSerializer serializer = new JsonSerializer(person);
        System.out.println(serializer.serialize());
    }
}