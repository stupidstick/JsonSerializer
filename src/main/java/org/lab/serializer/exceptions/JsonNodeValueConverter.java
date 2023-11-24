package org.lab.serializer.exceptions;

import org.lab.serializer.JsonContentParser;
import org.lab.serializer.JsonNodeConverter;
import org.lab.serializer.JsonRegexPatterns;

import java.util.regex.Matcher;

public class JsonNodeValueConverter {

    public static Object convert(String value) {
        Matcher matcher = JsonRegexPatterns.JSON_VALUE_PATTERN.matcher(value);
        if (!matcher.find())
            throw new NotJsonFormatException("Value " + value + " not in JSON format");
        if (matcher.group("strVal") != null) {
            return matcher.group("strVal");
        } else if (matcher.group("numVal") != null) {
            return Double.parseDouble(matcher.group("numVal"));
        } else if (matcher.group("boolVal") != null) {
            return Boolean.parseBoolean(matcher.group("boolVal").toLowerCase());
        } else if (matcher.group("null") != null) {
            return null;
        } else if (matcher.group("objVal") != null) {
            return JsonNodeConverter.convert(JsonContentParser.splitNodes(matcher.group("objVal"))
                    .toArray(String[]::new));
        } else if (matcher.group("arrVal") != null) {
            return null;
        }
        else throw new NotJsonFormatException("Value " + value + " not in JSON format");
    }
}
