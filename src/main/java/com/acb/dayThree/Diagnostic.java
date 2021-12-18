package com.acb.dayThree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on 18.12.21.
 */
public class Diagnostic {

    public record Binary (int value, List<Integer> bits){
        public static Binary of(String input){

            final List<Integer> bits = input.chars().map(Character::getNumericValue)
                    .boxed().collect(Collectors.toList());
            return new Binary(Integer.parseInt(input,2), bits);
        }

        public int get(final int index){
            return bits.get(index);
        }
    }

    final List<Binary> report;

    public Diagnostic(List<String> input) {
        report = input.stream().map(Binary::of).toList();
    }

    public int readPC(){

        Integer[] integers = new Integer[report.get(0).bits.size()];
        Arrays.fill(integers, 0);
        List<Integer> integerList = Arrays.asList(integers);
        for (Binary binary : report){
            IntStream.range(0, binary.bits.size())
                    .forEach(i -> integerList.set(i,binary.get(i)+integerList.get(i)));

        }
        StringBuilder gama = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        int size = report.size();
        for (Integer i : integerList) {
            if (i > (size / 2)) {
                gama.append("1");
                epsilon.append("0");
            } else {
                gama.append("0");
                epsilon.append("1");
            }
        }
        final int epsilonValue = Integer.parseInt(epsilon.toString(), 2);
        final int gamaValue = Integer.parseInt(gama.toString(), 2);
//        System.out.println(integerList);
//        System.out.printf("%s %s%n", gama, epsilon);
//        System.out.printf("%d %d%n", gamaValue, epsilonValue);
        return gamaValue * epsilonValue;
    }

    public int readLSR(){

        return readOGR() * readCS();
    }

    /**
     * To find oxygen generator rating, determine the most common value (0 or 1) in the current bit position,
     * and keep only numbers with that bit in that position. If 0 and 1 are equally common,
     * keep values with a 1 in the position being considered.
     */
    public int readOGR() {
        return genericRead(0, report, true);
    }

    public int readCS() {
        return genericRead(0, report, false);
    }

    private int genericRead(final  int index, List<Binary> binaries, boolean isOxygen){
        int total = binaries.stream().mapToInt(b -> b.get(index)).sum();
        int size = binaries.size();
        int mostCommon;

        if (isOxygen)
            mostCommon = total >= size/2.0 ? 1: 0;
        else
            mostCommon = total >= size/2.0? 0:1;

        final List<Binary> binaryStream = binaries.stream().filter(b -> b.bits.get(index) == mostCommon).toList();
        if (binaryStream.size() == 1){
            System.out.println(binaryStream.get(0).value);
            return binaryStream.get(0).value;
        } else {
            System.out.println(binaryStream);
            return genericRead(index+1, binaryStream,isOxygen);
        }
    }

    /**
     *      U result = identity;
     *      for (T element : this stream)
     *          result = accumulator.apply(result, element)
     *      return result;
     *
     *      <U> U reduce(U identity,
     *              BiFunction<U,? super T,U> accumulator,
     *              BinaryOperator<U> combiner)
     */
}
