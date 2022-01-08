package com.acb.daySix;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on 08.01.22.
 */
public class Lantern {

    public static final short PUBERTY=2; // days
    public static final short CYCLE=6; // 7 days including 0;


    public static final class SwarmTracker {
        private long[] count = new long[9];

        public static SwarmTracker of(final String input){
            final List<Integer> collect = Arrays.stream(input.split(","))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            return new SwarmTracker(collect);
        }

        public SwarmTracker(List<Integer> collect) {
            Arrays.fill(count, 0l);
            collect.forEach(i-> {
                count[i] = count[i]+1;
            });
        }

        public void tick(){
            long[] newCount = new long[9];

            System.arraycopy(count, 1, newCount, 0, count.length-1);
            newCount[CYCLE+PUBERTY] = count[0];
            newCount[CYCLE] = newCount[CYCLE]+count[0];

            this.count = newCount;
        }

        public long tick(int day){
            IntStream.range(0,day).forEach(i-> {
                System.out.println(String.format("day %d %d", i, count[8]));
                tick();
            });
            return size();
        }

        public long size(){
            return Arrays.stream(count).sum();
        }

    }
}
