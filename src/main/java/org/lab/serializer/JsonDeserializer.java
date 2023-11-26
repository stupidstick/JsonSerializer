package org.lab.serializer;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.lab.serializer.converters.JsonNodeConverter;
import org.lab.serializer.exceptions.JsonDeserializableObjectException;
import org.lab.serializer.exceptions.NotJsonFormatException;
import org.lab.serializer.nodes.JsonNodes;

import java.lang.reflect.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.lab.serializer.JsonRegexPatterns.JSON_CONTENT_PATTERN;

public class JsonDeserializer {
    private final String json;
    private final Class<?> deserializableClass;


    public JsonDeserializer(String json, Class<?> deserializableClass) {
        if (!JSON_CONTENT_PATTERN.matcher(json).find())
            throw new IllegalArgumentException("json string not in JSON format");
        this.json = json;
        this.deserializableClass = deserializableClass;
    }

    public Object deserialize(){
        Matcher matcher = JSON_CONTENT_PATTERN.matcher(json);
        if (matcher.find()) {
            try{
                String content = matcher.group("content");
                if (isContentNull(content)) return null;
                JsonNodes nodes = JsonNodeConverter.convert(JsonContentParser.splitNodes(content).toArray(String[]::new));
                return mapObject(nodes, createObject(deserializableClass));
            }
            catch (InstantiationException | IllegalAccessException | InvocationTargetException exception) {
                throw new JsonDeserializableObjectException();
            }
        }
        else throw new NotJsonFormatException("JSON content should be in {}");
    }

    private Object createObject(Class<?> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        var constructor = findNonParametrizedConstructor(clazz);
        return constructor.newInstance();
    }

    private Constructor<?> findNonParametrizedConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors)
            if (constructor.getParameters().length == 0)
                return constructor;
        throw new IllegalArgumentException("Class " + clazz.getName() + " does not have non-parameterized constructors");
    }

    private Object mapObject(JsonNodes nodes, Object obj) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (nodes.contains(field.getName())) {
                field.setAccessible(true);
                Object val = convertJsonNodeValueToType(nodes.getValue(field.getName()), field.getType());
                field.set(obj, val);
            }
        }
        return obj;
    }

    private Object convertJsonNodeValueToType(Object value, Class<?> type) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (value.getClass() == Double.class) {
            Double val = (Double) value;

            Class<?> primitiveType = ClassUtils.isPrimitiveWrapper(type) ? ClassUtils.wrapperToPrimitive(type) : type;
            if (primitiveType == int.class) {
                return val.intValue();
            } else if (primitiveType == long.class) {
                return val.longValue();
            } else if (primitiveType == short.class) {
                return val.shortValue();
            } else if (primitiveType == byte.class) {
                return val.byteValue();
            } else if (primitiveType == double.class) {
                return val;
            } else if (primitiveType == float.class) {
                return val.floatValue();
            } else throw new ClassCastException();
        } else if (value.getClass() == String.class) {
            String val = (String) value;
            if (type == String.class) {
                return val;
            } else if ((ClassUtils.isPrimitiveWrapper(type) ? ClassUtils.wrapperToPrimitive(type) : type) == char.class) {
                if (val.length() == 1)
                    return val.charAt(0);
                else throw new ClassCastException("The character value must consist of one character");
            } else throw new ClassCastException();
        } else if (value.getClass() == Boolean.class) {
            Class<?> primitiveType = ClassUtils.isPrimitiveWrapper(type) ? ClassUtils.wrapperToPrimitive(type) : type;
            if (primitiveType == boolean.class)
                return value;
            else throw new ClassCastException();
        }
        else if (value.getClass() == JsonNodes.class) {
            return mapObject((JsonNodes) value, createObject(type));
        } else if (value instanceof List<?>) {
            if (type.isArray()) {
                var list = (List<?>) value;
                Object array = Array.newInstance(type.getComponentType(), list.size());
                for (int i = 0; i < Array.getLength(array); i++) {
                    Array.set(array, i, convertJsonNodeValueToType(list.get(i), type.getComponentType()));
                }
                return array;
            } else throw new ClassCastException();
        } else if (value.getClass() == ObjectUtils.Null.class) {
            return null;
        } else throw new ClassCastException();
    }

    private boolean isContentNull(String content) {
        return Pattern.compile("^\\s*$").matcher(content).find();
    }
}

