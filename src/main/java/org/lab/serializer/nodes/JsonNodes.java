package org.lab.serializer.nodes;

import org.lab.serializer.exceptions.NotJsonFormatException;

import java.util.*;

public class JsonNodes {
    private final List<JsonNode> nodes = new ArrayList<>();

    public void add(JsonNode node) {
        if (contains(node.key()))
            throw new NotJsonFormatException("JSON must not contain duplicate keys");
        nodes.add(node);
    }

    public boolean contains(String key) {
        return nodes.stream().anyMatch((obj) -> Objects.equals(obj.key(), key));
    }

    public Object getValue(String key) {
        return nodes.stream().filter((node) -> Objects.equals(node.key(), key)).findFirst().orElseThrow().value();
    }
}
