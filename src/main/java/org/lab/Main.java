package org.lab;


import org.lab.entities.Dog;
import org.lab.entities.Person;
import org.lab.serializer.JsonDeserializer;
import org.lab.serializer.JsonSerializer;


public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Dog dog1 = new Dog("Volkodav", 20.4);
        Dog dog2 = new Dog("Bobik", 12.5);

        Person person = new Person(
                1,
                "Kirill",
                (byte) 19,
                'M',
                76.1,
                dog1,
                new Dog[] {dog1, dog2},
                new int[] {5, 25}
        );
        JsonSerializer serializer = new JsonSerializer(person);
        String json = serializer.serialize();
        System.out.println("original : " + person);
        System.out.println("json : " + json);
        JsonDeserializer deserializer = new JsonDeserializer(json, Person.class);
        Object object = deserializer.deserialize();


        System.out.println("deserializable object : " + object);
    }
}