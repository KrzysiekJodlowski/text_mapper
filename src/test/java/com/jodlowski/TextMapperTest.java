package com.jodlowski;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TextMapperTest {

    private TextMapper textMapper;
    private Map<Character, Set<String>> result;
    private Map<Character, Set<String>> expected;

    @Before
    public void initializeMapper() {
        this.textMapper = new TextMapper("Ala ma kota, kot KODUJE - w Javie kota!");
        this.result = textMapper.mapWordsToCharacters();
        this.expected = new LinkedHashMap<Character, Set<String>>() {
            {
                put('a', new TreeSet<>(Arrays.asList("ala", "javie", "kota", "ma")));
                put('d', new TreeSet<>(Collections.singletonList("koduje")));
                put('e', new TreeSet<>(Arrays.asList("javie", "koduje")));
                put('i', new TreeSet<>(Collections.singletonList("javie")));
                put('j', new TreeSet<>(Arrays.asList("javie", "koduje")));
                put('k', new TreeSet<>(Arrays.asList("koduje", "kot", "kota")));
                put('l', new TreeSet<>(Collections.singletonList("ala")));
                put('m', new TreeSet<>(Collections.singletonList("ma")));
                put('o', new TreeSet<>(Arrays.asList("koduje", "kot", "kota")));
                put('t', new TreeSet<>(Arrays.asList("kot", "kota")));
                put('u', new TreeSet<>(Collections.singletonList("koduje")));
                put('v', new TreeSet<>(Collections.singletonList("javie")));
                put('w', new TreeSet<>(Collections.singletonList("w")));
            }
        };
    }

    @Test
    public void testResultKeySetLength() {
        assertEquals(13, this.result.keySet().size());
    }

    @Test
    public void testIfAllLettersInResultKeySet() {
        assertTrue(this.result.keySet().containsAll(this.expected.keySet()));
    }
}
