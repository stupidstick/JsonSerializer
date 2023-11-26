package org.lab.serializer.converters;

import org.lab.serializer.nodes.JsonNode;
import org.lab.serializer.nodes.JsonNodes;
import org.lab.serializer.JsonRegexPatterns;
import org.lab.serializer.exceptions.NotJsonFormatException;

import java.util.*;
import java.util.regex.Matcher;

public class JsonNodeConverter {
    public static JsonNodes convert(String[] nodes) {
        JsonNodes nodesList = new JsonNodes();
        for (String node : nodes) {
            nodesList.add(convert(node));
        }
        return nodesList;
    }

    public static JsonNode convert(String node) {
        Matcher matcher = JsonRegexPatterns.JSON_NODE_PATTERN.matcher(node);
        if (!matcher.find()) throw new NotJsonFormatException("Node \"" + node + "\" not in JSON format");
        return new JsonNode(matcher.group("key"), Objects.requireNonNull(JsonNodeValueConverter.convert(matcher.group("value"))));
    }

    public static List<Object> convertArray(String[] arrayNodes) {
        return Arrays.stream(arrayNodes).map(JsonNodeValueConverter::convert).toList();
    }
}
