package org.lab.json.deserializer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonValidator {
    private final Pattern validatePattern = Pattern.compile("^[{\\[].*[}\\]]$");

    public boolean validate(String str) {
        Matcher matcher = validatePattern.matcher(str);
        return matcher.find();
    }
}
