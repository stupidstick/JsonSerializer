package org.lab;


import org.lab.entities.Person;
import org.lab.json.deserializer.JsonDeserializer;
import org.lab.json.deserializer.JsonValidator;
import org.lab.json.serializer.JsonSerializer;

public class Main {
    public static void main(String[] args) throws IllegalAccessException{
        Person person = new Person();
        JsonSerializer serializer = new JsonSerializer(person);
        String serializeStr = serializer.serialize();

        JsonDeserializer<Person> deserializer = new JsonDeserializer<>("{fgfd}");

    }
}
