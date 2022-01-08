package daySix;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on 08.01.22.
 */
public class Lantern {

    public static final int PUBERTY=2; // days
    public static final int CYCLE=6; // 7 days including 0;
    private int dayLeft;

    public Lantern(){
        this(CYCLE+PUBERTY);
    }
    public Lantern(int dayLeft) {
        this.dayLeft = dayLeft;
    }

    public boolean tick(){
        if (dayLeft == 0){
            dayLeft = CYCLE;
            return true;
        } else {
            dayLeft--;
            return false;
        }
    }

    public static final class Swarm {
        private List<Lantern> lanterns;

        public Swarm(List<Lantern> lanterns) {
            this.lanterns = lanterns;
        }

        public static Swarm of(final String input){
            final List<Lantern> collect = Arrays.stream(input.split(","))
                    .mapToInt(Integer::parseInt)
                    .mapToObj(Lantern::new)
                    .collect(Collectors.toList());
            return new Swarm(collect);
        }

        public int tick(int day){
            IntStream.range(0,day).forEach(i-> tick());
            return lanterns.size();
        }

        public int tick(){
            final List<Lantern> newLanterns = this.lanterns.stream()
                    .filter(Lantern::tick)
                    .map(l -> new Lantern())
                    .toList();
            lanterns.addAll(newLanterns);
            return lanterns.size();
        }
    }
}
