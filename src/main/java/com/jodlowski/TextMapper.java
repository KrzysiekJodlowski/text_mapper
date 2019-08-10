package com.jodlowski;

import java.util.*;
import java.util.stream.Stream;


public class TextMapper {

    String inputText;
    private final int ZERO = 0;

    public TextMapper(String inputText) {
        this.inputText = inputText;
    }

    public Map<Character, Set<String>> mapWordsToCharacters() {
        Map<Character, Set<String>> result = new LinkedHashMap<>();
        this.getStreamOfChars().forEach(character -> result.put(character, new TreeSet<>()));
        return result;
    }

    private Stream<Character> getStreamOfChars() {
        return Arrays.stream(inputText.split("[^a-zA-Z]+"))
                .map(String::toLowerCase)
                .flatMap(word -> Arrays.stream(word.split("")))
                .map(str -> str.charAt(this.ZERO))
                .distinct()
                .sorted();
    }
}
