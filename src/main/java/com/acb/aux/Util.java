package com.acb.aux;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 08.01.22.
 */
public class Util {

    public static List<Integer> readIntegers(final String line){
        return line.chars().map(Character::getNumericValue).boxed().toList();
    }

    public static List<String> readCharacters(final String line){
        return Arrays.stream(line.chars().mapToObj(c -> (char) c).toArray(Character[]::new)).map(String::valueOf).toList();
    }

}
