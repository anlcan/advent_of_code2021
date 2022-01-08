package com.acb.dayTen;

import com.acb.aux.Util;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 08.01.22.
 */
public class Parser {
    /*
    If a chunk opens with (, it must close with ).
    If a chunk opens with [, it must close with ].
    If a chunk opens with {, it must close with }.
    If a chunk opens with <, it must close with >.

    ): 3 points.
    ]: 57 points.
    }: 1197 points.
    >: 25137 points.
     */
    public enum Delimiter {
        P("<",">",25137),
        Q("(",")", 3),
        R("{","}", 1197),
        S("[","]", 57);

        public final String open;
        public final String close;
        private final int value;

        Delimiter(String open, String close, int value) {
            this.open = open;
            this.close = close;
            this.value = value;
        }

        public String getClose() {
            return close;
        }

        public String getOpen() {
            return open;
        }

        public int getValue() {
            return value;
        }

        public static Delimiter forCloser(final String closer){
            return Arrays.stream(Delimiter.values()).filter(d -> d.close.equalsIgnoreCase(closer)).findFirst().get();
        }
    }

    final static List<String> CLOSER = Arrays.stream(Delimiter.values()).map(Delimiter::getClose).collect(Collectors.toList());
    final static List<String> OPENER = Arrays.stream(Delimiter.values()).map(Delimiter::getOpen).collect(Collectors.toList());

    final Deque<String> stack = new ArrayDeque<>();
    final boolean corrupted;
    final boolean completed;

    final int errorScore;

    public Parser(final String string) {
        List<String> input = Util.readCharacters(string);

        for (String s : input) {
            if (OPENER.contains(s)){
                stack.push(s);
            } else {
                if (Delimiter.forCloser(s).getOpen().equals(stack.peekFirst())){
                    stack.pop();
                } else {
                    corrupted = true;
                    completed = false;
                    errorScore = Delimiter.forCloser(s).getValue();
                    return;
                }
            }
        }
        corrupted = false;
        completed = stack.size() ==0;
        errorScore = 0;
    }

    public boolean isCorrupted() {
        return corrupted;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getErrorScore() {
        return errorScore;
    }

    public static int score(List<String> input){
        return input.stream().map(Parser::new).filter(Parser::isCorrupted).mapToInt(Parser::getErrorScore).sum();
    }
}
