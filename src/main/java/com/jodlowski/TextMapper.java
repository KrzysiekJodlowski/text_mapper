package com.jodlowski;

import java.util.*;
import java.util.stream.IntStream;


public class TextMapper {

    public TextMapper(String inputText) {
    }

    public Map<Character, Set<String>> mapWordsToCharacters() {
        Map<Character, Set<String>> result = new LinkedHashMap<>();
        IntStream.range(0, 13).forEach(x -> result.put((char) x, new TreeSet<>()));
        return result;
    }
}
