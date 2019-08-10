package com.jodlowski;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


public class TextMapperTest {

    private Map<Character, Set<String>> result;
    private Map<Character, Set<String>> expected;

    @Before
    public void initializeMapper() {
        TextMapper textMapper = new TextMapper("Ala ma kota, kot KODUJE - w Javie kota!");
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

    @Test
    public void testResultKeySetAlphabeticOrder() {
        Iterator keySetIterator =  this.result.keySet().iterator();
        assertEquals('a', keySetIterator.next());
        assertEquals('d', keySetIterator.next());
        assertEquals('e', keySetIterator.next());
    }

    @Test
    public void testResultValues() {
        assertEquals(this.expected.get('a'), this.result.get('a'));
        assertEquals(this.expected.get('k'), this.result.get('k'));
        assertEquals(this.expected.get('m'), this.result.get('m'));
    }

    @Test
    public void testPunctuationMarksInsensitivity() {
        assertFalse(this.result.containsKey("-"));
        assertFalse(this.result.get('a').contains("kota,"));
        assertFalse(this.result.get('a').contains("kota!"));
    }

    @Test
    public void testCaseInsensitivity() {
        assertFalse(this.result.containsKey('J'));
        assertFalse(this.result.get('j').contains("Javie"));
        assertFalse(this.result.containsKey('K'));
        assertFalse(this.result.get('k').contains("KODUJE"));
    }

    @Test
    public void testResultValuesAlphabeticOrder() {
        Iterator letterAValuesIterator = this.result.get('a').iterator();
        assertEquals("ala", letterAValuesIterator.next());
        assertEquals("javie", letterAValuesIterator.next());
        assertEquals("kota", letterAValuesIterator.next());
        assertEquals("ma", letterAValuesIterator.next());
    }

    @Test
    public void testIfThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TextMapper(null));
    }

    @Test
    public void testIfThrowsInputMismatchException() {
        assertThrows(InputMismatchException.class, () -> new TextMapper(""));
    }
}
