package org.lab.serializer;

import org.apache.commons.lang3.ObjectUtils;
import org.lab.serializer.exceptions.JsonNodeValueConverter;
import org.lab.serializer.exceptions.NotJsonFormatException;

import java.util.*;
import java.util.regex.Matcher;

public class JsonNodeConverter {
    public static Map<String, Object> convert(String[] nodes) {
        Map<String, Object> nodesMap = new HashMap<>();
        for (String node : nodes) nodesMap.putAll(convert(node));
        return nodesMap;
    }

    public static Map<String, Object> convert(String node) {
        Matcher matcher = JsonRegexPatterns.JSON_NODE_PATTERN.matcher(node);
        if (!matcher.find()) throw new NotJsonFormatException("Node \"" + node + "\" not in JSON format");
        return Map.of(matcher.group("key"), Objects.requireNonNull(JsonNodeValueConverter.convert(matcher.group("value"))));
    }

    public static List<Object> convertArray(String[] arrayNodes) {
        return Arrays.stream(arrayNodes).map(JsonNodeValueConverter::convert).toList();
    }
}
