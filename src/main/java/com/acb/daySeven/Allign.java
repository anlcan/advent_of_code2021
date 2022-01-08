package com.acb.daySeven;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

/**
 * Created on 08.01.22.
 */
public class Allign {

    private final List<Integer> init;
    private int bestLinearCostLine = -1;
    private int bestIncCostLine = -1;

    public Allign(List<Integer> init) {
        this.init = init;
    }

    public static Allign of(String input) {
        final List<Integer> collect = Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        return new Allign(collect);
    }

    public int linearCostAt(int target){
        return init.stream().mapToInt(i -> abs(target-i)).sum();
    }

    public int incCostAt(int target){
        return init.stream().mapToInt(i -> sumAt(abs(target-i))).sum();
    }

    private int sumAt(int target){
        return IntStream.rangeClosed(0, target).sum();
    }

    public int bestLinear(){
        final int max = init.stream().mapToInt(v -> v).max().getAsInt();
        final int min = init.stream().mapToInt(v -> v).min().getAsInt();
        final List<Integer> integers = IntStream.range(min, max).map(this::linearCostAt).boxed().toList();

        final int minGasSpent = integers.stream().mapToInt(v -> v).min().getAsInt();
        bestLinearCostLine = integers.indexOf(minGasSpent);
        return minGasSpent;
    }

    public int bestInc(){
        final int max = init.stream().mapToInt(v -> v).max().getAsInt();
        final int min = init.stream().mapToInt(v -> v).min().getAsInt();
        final List<Integer> integers = IntStream.range(min, max).map(this::incCostAt).boxed().toList();

        final int minGasSpent = integers.stream().mapToInt(v -> v).min().getAsInt();
        bestIncCostLine = integers.indexOf(minGasSpent);
        return minGasSpent;
    }

    public int getBestLinearCostLine() {
        return bestLinearCostLine;
    }

    public int getBestIncCostLine() {
        return bestIncCostLine;
    }
}
