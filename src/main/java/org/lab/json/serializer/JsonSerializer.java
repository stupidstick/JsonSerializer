package org.lab.json.serializer;

import org.apache.commons.lang3.ClassUtils;
import org.lab.json.annotations.JsonSerializable;
import org.lab.json.annotations.ToSerialize;
import org.lab.json.serializer.exceptions.NotJsonSerializableException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

public class JsonSerializer {
    private final Object serializableObj;

    public JsonSerializer(Object serializableObj) {
        if (serializableObj == null)
            throw new NullPointerException("Serializable object cannot be null");
        if (!serializableObj.getClass().isAnnotationPresent(JsonSerializable.class))
            throw new IllegalArgumentException("Serializable object must be annotated @JsonSerializable");
        this.serializableObj = serializableObj;
    }


    public String serialize() throws IllegalAccessException {
        StringBuilder builder = new StringBuilder("{");
        Class<?> clazz = serializableObj.getClass();

        Field[] fields = findAnnotatedFields(clazz);
        for (Field field : fields) {
            builder.append(serializeField(field));

            if (fields[fields.length - 1] != field) {
                builder.append(',');
            }

        }

        builder.append("}");
        return builder.toString();
    }



    private String serializeField(Field field) throws IllegalAccessException {
        field.setAccessible(true);
        if (field.getType().isArray()) {
            return serializeArrayField(field);
        }
        else {
            return serializeObjectField(field);
        }
    }

    private String serializeArrayField(Field arrayField) throws IllegalAccessException {
        if (!(isValidType(arrayField.getType().getComponentType()) || isJsonSerializableAnnotated(arrayField.getType().getComponentType())))
            throw new NotJsonSerializableException(arrayField.getName() + " field must be serializable");

        Object arr = arrayField.get(serializableObj);

        StringBuilder builder = new StringBuilder("\"").append(arrayField.getName()).append("\":[");
        for (int i = 0; i < Array.getLength(arr); i++) {
            builder.append(formatVal(Array.get(arr, i)));
            if (i != Array.getLength(arr) - 1)
                builder.append(',');
        }
        return builder.append("]").toString();
    }

    private String serializeObjectField(Field field) throws IllegalAccessException {
        if (!(isValidType(field.getType()) || isJsonSerializableAnnotated(field.getType())))
            throw new NotJsonSerializableException(field.getName() + " field must be serializable");

        StringBuilder builder = new StringBuilder("\"").append(field.getName()).append("\":");

        Object value = field.get(serializableObj);
        builder.append(formatVal(value));

        return builder.toString();
    }

    private String formatVal(Object val) throws IllegalAccessException{
        if (val == null) {
            return "null";
        }
        Class<?> type = val.getClass();
        if (isJsonSerializableAnnotated(type)) {
            return new JsonSerializer(val).serialize();
        } else if (type == String.class || type == Character.class) {
            return "\"" + val + "\"";
        } else {
            return val.toString();
        }
    }

    private Field[] findAnnotatedFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(ToSerialize.class))
                .toArray(Field[]::new);
    }

    private boolean isValidType(Class<?> clazz) {
        return ClassUtils.isPrimitiveOrWrapper(clazz) || (clazz.equals(String.class));
    }

    private boolean isJsonSerializableAnnotated(Class<?> clazz) {
        return clazz.isAnnotationPresent(JsonSerializable.class);
    }
}