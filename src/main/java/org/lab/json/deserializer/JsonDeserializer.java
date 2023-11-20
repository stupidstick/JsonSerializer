package org.lab.json.deserializer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonDeserializer<T> {
    private static final Pattern JSON_BLOCK_PATTERN = Pattern.compile("^\\{(.*)}$");
    private static final Pattern JSON_STRING_PATTERN = Pattern.compile("\\s*\"([^\"\\s]+)\"\\s*:\\s*\"([^\"]*)\"\\s*");
    private static final Pattern JSON_NONSTRING_PATTERN = Pattern.compile("\\s*\"([^\"\\s]+)\"\\s*:\\s*([^\"\\s]*)\\s*");
    private static final Pattern JSON_ARRAY_PATTERN = Pattern.compile("\\s*\"([^\"\\s]+)\"\\s*:\\s*\\[(.*)](\\s*)");
    private static final Pattern JSON_OBJECT_PATTERN = Pattern.compile("\\s*\"([^\"\\s]+)\"\\s*:\\s*\\{(.*)}(\\s*)");
    private final String jsonString;
    private final Map<String, Object> contentMap = new HashMap<>();

    public JsonDeserializer(String json) {
        Matcher matcher = JSON_BLOCK_PATTERN.matcher(json);
        if (!matcher.find())
            throw new IllegalArgumentException("Argument is not in JSON format");
        this.jsonString = json;
    }

    public void deserialize() {
        jsonToMap(jsonString);

    }

    private Map<String, Object> jsonToMap(String json) {
        String content = JSON_BLOCK_PATTERN.matcher(json).group(1);
        String[] kvals = content.split(",");

        for (String kval : kvals) {

        }

        for (String kval : kvals) {
            System.out.println(kval);
        }

        return new HashMap<>();
    }


}
