package org.lab;


import com.florianingerl.util.regex.Pattern;
import org.lab.entities.Person;
import org.lab.serializer.JsonDeserializer;
import org.lab.serializer.JsonSerializer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person();
        JsonSerializer serializer = new JsonSerializer(person);
        System.out.println(serializer.serialize());
        String string = "{\"str\":null,\"name\":\"AOC\",\"age\":12,\"dval\":5.5,\"shortval\":10,\"bool\":true,\"dog\":{\"name\":\"Volkodav\",\"weight\":12.5},\"arr\":[{\"name\":\"Basya\",\"weight\":20.0},{\"name\":\"Bobik\",\"weight\":10.0},{\"name\":\"Muhtar\",\"weight\":13.5}, 12.5]}";
        JsonDeserializer<String> deserializer = new JsonDeserializer<>(string);
        deserializer.deserialize();
    }
}