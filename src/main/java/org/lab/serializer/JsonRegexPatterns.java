package org.lab.serializer;

import java.util.regex.Pattern;

public class JsonRegexPatterns {
    public static final Pattern JSON_CONTENT_PATTERN = Pattern.compile("^\\s*\\{(?<content>.*)}\\s*$");
    public static final Pattern JSON_VALUE_PATTERN = Pattern.compile("^\\s*(\"(?<strVal>(\\\\\"|[^\"])*)\"|" +
            "(?<numVal>[+-]?[0-9]+(\\.[0-9]+)?)|(?<boolVal>true|false)|(?<nullVal>null)|\\{(?<objVal>.*)}|\\[(?<arrVal>.*)])\\s*$");
    public static final Pattern JSON_NODE_PATTERN = Pattern.compile("^\\s*\"(?<key>[^\"]+)\"\\s*:\\s*(?<value>.*)$");

}
