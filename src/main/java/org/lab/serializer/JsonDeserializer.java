package org.lab.serializer;

import org.apache.commons.lang3.StringUtils;
import org.lab.serializer.exceptions.NotJsonFormatException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonDeserializer<T> {
    private final Pattern JSON_CONTENT_PATTERN = Pattern.compile("^\\s*\\{(?<content>.*)}\\s*$");
    private final List<Character> ignoredWhitespaces = List.of(' ', '\r', '\t', '\n');
    private String json;


    public JsonDeserializer(String json) {
        if (!JSON_CONTENT_PATTERN.matcher(json).find())
            throw new IllegalArgumentException("json string not in JSON format");
        this.json = json;
    }

    public void deserialize() {

    }

    private Map<String, Object> parse(String json) {
        int depth = 0;
        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();
        boolean inQuotes = false;
        boolean inKey = true;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{' && !inQuotes) {
                depth++;
            }
            else if (c == '}' && !inQuotes) {
                depth--;
            }
            else if (c == '\"' && !json.substring(i - 1, i).equals("\\\"")) {
                inQuotes = !inQuotes;
            }
            else if (c == ':' && !inQuotes){
                inKey = !inKey;
            }
            else if (inQuotes) {
                if (inKey) {
                    key.append(c);
                }
                else{
                    value.append(c);
                }
            }
            else if (!inKey && !ignoredWhitespaces.contains(c)) {
                value.append(c);
            }
            else if (c == ',') {
                System.out.println();
            }
        }

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == '{' && !inQuotes) {
                depth++;
            } else if (c == '}' && !inQuotes) {
                depth--;
            } else if (c == '\"' && String.valu != '\\' + '\"') {

            }
        }
    }

    private record ExtractResult(String content, String[] extracted) {
    }

    ;
}
