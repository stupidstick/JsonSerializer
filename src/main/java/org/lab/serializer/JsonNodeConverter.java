package org.lab.serializer;

import org.lab.serializer.exceptions.NotJsonFormatException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class JsonNodeConverter {


    public static Map<String, Object> convert(String[] nodes) {
        Map<String, Object> nodesMap = new HashMap<>();
        for (String node : nodes) nodesMap.putAll(convert(node));
        return nodesMap;
    }

    public static Map<String, Object> convert(String node) {
        Matcher matcher = JsonRegexPatterns.JSON_NODE_PATTERN.matcher(node);
        if (matcher.find()) {
            String key = matcher.group("key");
            String value = matcher.group("value");

        }
        else throw new NotJsonFormatException("Node \"" + node + "\" not in JSON format");
    }
}
