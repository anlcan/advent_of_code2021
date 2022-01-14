package com.acb.dayTen;

import com.acb.aux.Util;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.function.Predicate;
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
    errorScore
        ): 3 points.
        ]: 57 points.
        }: 1197 points.
        >: 25137 points.
    completionScore:
        ): 1 point.
        ]: 2 points.
        }: 3 points.
        >: 4 points.
     */
    public enum Delimiter {
        P("<",">",25137, 4),
        Q("(",")", 3,1),
        R("{","}", 1197, 3),
        S("[","]", 57, 2);

        public final String open;
        public final String close;
        private final int errorScore;
        private final int compScore;

        Delimiter(String open, String close, int errorScore, int completionScore) {
            this.open = open;
            this.close = close;
            this.errorScore = errorScore;
            this.compScore = completionScore;
        }

        public String getClose() {
            return close;
        }

        public String getOpen() {
            return open;
        }

        public int getErrorScore() {
            return errorScore;
        }

        public int getCompScore() {
            return compScore;
        }

        public static Delimiter forCloser(final String closer){
            return Arrays.stream(Delimiter.values()).filter(d -> d.close.equalsIgnoreCase(closer)).findFirst().get();
        }

        public static Delimiter forOpener(final String opener){
            return Arrays.stream(Delimiter.values()).filter(d -> d.open.equalsIgnoreCase(opener)).findFirst().get();
        }
    }

    final static List<String> CLOSER = Arrays.stream(Delimiter.values()).map(Delimiter::getClose).collect(Collectors.toList());
    final static List<String> OPENER = Arrays.stream(Delimiter.values()).map(Delimiter::getOpen).collect(Collectors.toList());

    final Deque<String> stack = new ArrayDeque<>();
    final boolean corrupted;
    final boolean completed;

    final int errorScore;

    public String getCompString() {
        return compString;
    }

    String compString;

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
                    errorScore = Delimiter.forCloser(s).getErrorScore();
                    return;
                }
            }
        }
        corrupted = false;
        completed = stack.size() == 0;
        errorScore = 0;
    }
    /*
    Start with a total score of 0.
    Then, for each character, multiply the total score by 5 and then increase the total score
    by the point value given for the character in the following table:
     */
    public long complete(){
        StringBuilder sb = new StringBuilder();
        long completionScore = 0;
        while (stack.size()>0){
            final Delimiter delimiter = Delimiter.forOpener(stack.pop());
            sb.append(delimiter.getClose());
            completionScore = (completionScore * 5) + delimiter.getCompScore();
        }
        compString = sb.toString();
        return completionScore;
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

    public static int getErrorScore(List<String> input){
        return input.stream().map(Parser::new).filter(Parser::isCorrupted).mapToInt(Parser::getErrorScore).sum();
    }

    /*
     the winner is found by sorting all of the scores and then taking the middle score.
     */
    public static long getCompletionScore(List<String> input){
        final List<Long> integers = input.stream().map(Parser::new)
                .filter(Predicate.not(Parser::isCompleted))
                .filter(Predicate.not(Parser::isCorrupted))
                .mapToLong(Parser::complete)
                .sorted()
                .boxed()
                .toList();
        System.out.println(integers);
        return integers.stream().skip(integers.size()/2).findFirst().get();
    }
}
