package com.acb.aux;

import java.util.List;

/**
 * Created on 08.01.22.
 */
public class Util {

    public static List<Integer> readIntegers(final String line){
        return line.chars().map(Character::getNumericValue).boxed().toList();
    }
}
