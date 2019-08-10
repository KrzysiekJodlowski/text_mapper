package com.jodlowski;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TextMapper {

    private List<String> listOfWords;
    private final int ZERO = 0;

    public TextMapper(String inputText) {
        this.checkInputText(inputText);
        this.listOfWords = this.getListOfWords(inputText);
    }

    public Map<Character, Set<String>> mapWordsToCharacters() {

        return this.getStreamOfChars()
                .collect(Collectors.toMap(Character::new,
                        this.getWordsContainingCharacter(),
                        (x, y) -> x,
                        LinkedHashMap::new));
    }

    private Stream<Character> getStreamOfChars() {
        return this.listOfWords.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .map(str -> str.charAt(this.ZERO))
                .distinct()
                .sorted();
    }

    private List<String> getListOfWords(String inputText) {
        return Arrays.stream(inputText.split("[^a-zA-Z]+"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    private Function<Character, Set<String>> getWordsContainingCharacter() {
        return character ->
                this.listOfWords.stream()
                        .filter(word -> word.indexOf(character) >= this.ZERO)
                        .collect(Collectors.toCollection(TreeSet::new));
    }

    private void checkInputText(String inputText) {
        if (inputText == null ) {
            throw new NullPointerException("Input text value is null!");
        } else if (inputText.length() == this.ZERO) {
            throw new InputMismatchException("Input text can't be empty!");
        }
    }
}
