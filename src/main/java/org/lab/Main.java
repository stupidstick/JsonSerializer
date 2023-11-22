package org.lab;


import com.florianingerl.util.regex.Pattern;
import org.lab.entities.Person;
import org.lab.serializer.JsonDeserializer;
import org.lab.serializer.JsonSerializer;

public class Main {
    public static void main(String[] args) throws IllegalAccessException{
        Person person = new Person();
        JsonSerializer serializer = new JsonSerializer(person);
        System.out.println(serializer.serialize());

    }
}