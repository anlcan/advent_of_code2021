package com.acb;

import java.io.BufferedInputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2019-01-21.
 */
public class Util {

    public static final String ADVENT_PREFIX = "https://adventofcode.com/2021/day/%d/input";

    public static List<Integer> readIntegers(final int day){
        final String url_path = String.format(ADVENT_PREFIX, day);
//        return readInteger(url_path).var;

        try {
            java.net.URL url = new java.net.URL(url_path);
            String content = new String(new BufferedInputStream(url.openStream()).readAllBytes(), "UTF8");
            return Arrays.stream(content.split("\n")).map(Integer::valueOf).collect(Collectors.toList());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Integer> readInteger(final String path) {

        try {
            java.net.URL url = Util.class.getResource(path);
            String content = new String(java.nio.file.Files.readAllBytes(Paths.get(url.toURI())), "UTF8");
            return Arrays.stream(content.split("\n")).map(Integer::valueOf).collect(Collectors.toList());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readStrings(final String path) {


        try {
            java.net.URL url = Util.class.getResource(path);
            String content = new String(java.nio.file.Files.readAllBytes(Paths.get(url.toURI())), "UTF8");
            return Arrays.stream(content.split("\n")).collect(Collectors.toList());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readStringsDoubleNewline(final String path) {


        try {
            java.net.URL url = Util.class.getResource(path);
            String content = new String(java.nio.file.Files.readAllBytes(Paths.get(url.toURI())), "UTF8");
            return Arrays.stream(content.split("\n\n")).collect(Collectors.toList());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
