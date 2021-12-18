package com.acb.dayThree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on 18.12.21.
 */
public class Diagnostic {

    public record PowerConsumption (int gama, int epsilon){
        public int check(){
            return gama * epsilon;
        }
    }

    public record Binary (int value, List<Integer> bits){
        public static Binary of(String input){
//            int decimal=
            final List<Integer> bits = input.chars().map(Character::getNumericValue)
                    .boxed().collect(Collectors.toList());
            return new Binary(Integer.parseInt(input,2), bits);
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

    final List<String> report;

    public Diagnostic(List<String> report) {
        this.report = report;
    }

    public int readPC(){
        List<Binary> binaries = report.stream().map(Binary::of).toList();
//        IntStream.of(binaries.get(0).bits.size()).mapToObj()

        Integer[] integers = new Integer[binaries.get(0).bits.size()];
        Arrays.fill(integers, 0);
        List<Integer> integerList = Arrays.asList(integers);
        for (Binary binary : binaries){
            IntStream.range(0, binary.bits.size())
                    .forEach(i -> integerList.set(i,binary.bits.get(i)+integerList.get(i)));

        }
        StringBuilder gama = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        int size = binaries.size();
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
        System.out.println(integerList);
        System.out.println(String.format("%s %s", gama, epsilon));
        System.out.println(String.format("%d %d", gamaValue, epsilonValue));
        return gamaValue * epsilonValue;
    }

//    public static List<Integer> numbers =
//    public static List<String> transpose(List<String> input){
//        final List<Integer> result = Lists.of(0, 0,0,0,0,0);
//
//
//        input.forEach(binary -> {
//
//            for (int i = 0; i < binary.length(); i++) {
//                result.set(i, result.get(i) + binary.charAt(i));
//            }
//        }
//        });
//
//    }

}
