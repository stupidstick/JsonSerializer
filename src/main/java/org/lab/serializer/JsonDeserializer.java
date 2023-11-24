package org.lab.serializer;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class JsonDeserializer<T> {
    private String json;


    public JsonDeserializer(String json) {
        if (!JSON_CONTENT_PATTERN.matcher(json).find())
            throw new IllegalArgumentException("json string not in JSON format");
        this.json = json;
    }

    public void deserialize() {
        Matcher matcher = JSON_CONTENT_PATTERN.matcher(json);
        if (matcher.find()) {
            String content = matcher.group("content");
            for (String splitByIndex : splitByIndices(content, findDelimiterIndices(content))) {
                System.out.println(splitByIndex);
            }
        }
        else {
            System.out.println("Нихуя не найдено");;
        }
    }

    private Map<String, Object> convertNodesToMap() {

    }


}
