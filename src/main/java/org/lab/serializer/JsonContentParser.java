package org.lab.serializer;

import org.lab.serializer.exceptions.NotJsonFormatException;

import java.util.ArrayList;
import java.util.List;

public class JsonContentParser {
    public static List<String> splitNodes(String content) {
        return splitByIndices(content, findDelimiterIndices(content));
    }

    private static List<Integer> findDelimiterIndices(String content) {
        List<Integer> indices = new ArrayList<>();
        ParserPosition position = ParserPosition.OTHER;
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == '\"') {
                position = getQuotesParserPosition(content, position, i);
            } else if (position == ParserPosition.QUOTES)
                continue;

            switch (c) {
                case ',' -> indices.add(i);
                case '{','[' -> i = findBlockEnd(i, content);
            }
        }
        return indices;
    }

    private static int findBlockEnd(int startIndex, String content) {
        char startType = content.charAt(startIndex);
        char endType = switch (startType) {
            case '{' -> '}';
            case '[' -> ']';
            default -> throw new IllegalArgumentException("Illegal block symbol: " + startType);
        };
        int depth = 0;
        ParserPosition position = ParserPosition.OTHER;
        int i = startIndex;
        for (; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == '\"') {
                position = getQuotesParserPosition(content, position, i);
            } else if (position == ParserPosition.QUOTES)
                continue;
            if (c == startType)
                depth++;
            else if (c == endType)
                depth--;
            if (depth == 0)
                break;
        }
        if (i == content.length()) {
            throw new NotJsonFormatException("Block " + content.substring(startIndex) + " not in JSON format");
        }
        return i;
    }

    private static ParserPosition getQuotesParserPosition(String content, ParserPosition position, int i) {
        if (i == 0)
            position = ParserPosition.QUOTES;
        else if (!content.substring(i - 1, i).equals("\\\"")) {
            position = switch (position) {
                case OTHER -> ParserPosition.QUOTES;
                case QUOTES -> ParserPosition.OTHER;
            };
        }
        return position;
    }

    private static List<String> splitByIndices(String content, List<Integer> indices) {
        List<String> substrings = new ArrayList<>();
        StringBuilder substring = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            if (indices.contains(i)) {
                substrings.add(substring.toString());
                substring.setLength(0);
                continue;
            }
            substring.append(content.charAt(i));
        }
        substrings.add(substring.toString());
        return substrings;
    }

    private enum ParserPosition {
        QUOTES, OTHER
    }
}
