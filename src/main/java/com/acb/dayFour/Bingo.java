package com.acb.dayFour;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 18.12.21.
 */
public class Bingo {

    public static final class Card {
        public final List<List<Integer>> verticals;
        public final List<List<Integer>> horizontals;

        public final LinkedList<Integer> calledNumbers = new LinkedList<>();


        public Card(List<List<Integer>> numbers) {
            verticals = List.copyOf(numbers);
            horizontals = new ArrayList<>();

            for (int i = 0; i < verticals.size(); i++) {
                horizontals.add(new ArrayList<>());
                for (int j = 0; j < verticals.get(i).size(); j++){
                    horizontals.get(i).add( verticals.get(j).get(i));
                }
            }
        }

        public boolean next(final Integer nextNumber){
            calledNumbers.add(nextNumber);
            return verticals.stream().anyMatch(calledNumbers::containsAll) || horizontals.stream().anyMatch(calledNumbers::containsAll);
        }
        /*
        The score of the winning board can now be calculated.
        Start by finding the sum of all unmarked numbers on that board;
        in this case, the sum is 188. Then, multiply that sum by the number that was just called when the board
         */
        public long score(){
            final Integer last = calledNumbers.getLast();
            final List<Integer> collect = verticals.stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
            collect
                    .removeAll(calledNumbers);
            return collect.stream().mapToInt(Integer::intValue).sum() * last;
        }

    }

    public List<Card> cards = new ArrayList<>();

        /**
         *
         * @param input:
         *  3 15  0  2 22
         *  9 18 13 17  5
         * 19  8  7 25 23
         * 20 11 10 24  4
         * 14 21 16 12  6
         */
    public void addCard(final String input){
        final List<List<Integer>> integers = Arrays.stream(input.split("\n"))
                .map(s-> Arrays.stream(s.split(" "))
                        .filter(l-> l.length()>0)
                        .mapToInt(Integer::parseInt)
                        .boxed().toList()).collect(Collectors.toList());
        Card c = new Card(integers);
        cards.add(c);
    }

    public long play(final List<Integer> numbers){

        for(Integer i : numbers){
//            final Optional<Card> first = cards.stream().filter(c -> c.next(i)).findFirst();
//            if (first.isPresent()){
//                return first.get().score();
//            }
            for(Card c: cards){
                if (c.next(i)){
                    System.out.println(c.verticals);
                    System.out.println(c.calledNumbers);
                    return c.score();
                }
            }
        }
        throw new RuntimeException("no dice");
    }


}
