package org.lab.serializer;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.lab.serializer.JsonRegexPatterns.JSON_CONTENT_PATTERN;

public class JsonDeserializer<T> {
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
            Map<String, Object> map = JsonNodeConverter.convert(JsonContentParser.splitNodes(content).toArray(String[]::new));
            System.out.println(map.get("arr"));
        }
    }
}
