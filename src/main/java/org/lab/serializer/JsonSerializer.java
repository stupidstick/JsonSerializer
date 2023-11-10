package org.lab.serializer;


import org.lab.serializer.annotations.JsonSerializable;
import org.lab.serializer.annotations.ToSerialize;

import java.lang.reflect.Field;
import java.util.Arrays;

public class JsonSerializer {
    private final Object serializableObj;

    public JsonSerializer(Object serializableObj) {
        if (serializableObj == null)
            throw new NullPointerException("Serializable object cannot be null");
        if (serializableObj.getClass().isAnnotationPresent(JsonSerializable.class))
            throw new IllegalArgumentException("Serializable object must be annotated @JsonSerializable");
        this.serializableObj = serializableObj;
    }


//    public String serialize() {
//    }

    private Field[] findToSerializeFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields()).toArray(Field[]::new);
    }
}