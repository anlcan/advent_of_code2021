package daySeven;

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
    private int bestLine = -1;

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

    public int costAt(int target){
        return init.stream().mapToInt(i -> abs(target-i)).sum();
    }


    public int best(){
        final int max = init.stream().mapToInt(v -> v).max().getAsInt();
        final int min = init.stream().mapToInt(v -> v).min().getAsInt();
        final List<Integer> integers = IntStream.range(min, max).map(this::costAt).boxed().toList();

        final int minGasSpent = integers.stream().mapToInt(v -> v).min().getAsInt();
        bestLine = integers.indexOf(minGasSpent);
        return minGasSpent;
    }

    public int getBestLine() {
        return bestLine;
    }
}
