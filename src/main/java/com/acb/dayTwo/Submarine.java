package com.acb.dayTwo;

import java.util.List;

/**
 * Created on 18.12.21.
 */
public class Submarine {

    private record Position (int horizontal,  int depth){
        public Position () {
            this(0,0);
        }

        public Position apply(final Command c){
            return switch (c.direction){
                case "forward" ->   new Position(this.horizontal + c.value, this.depth);
                case "down" ->      new Position(this.horizontal , this.depth+ c.value);
                case "up" ->         new Position(this.horizontal , this.depth - c.value);
                default -> throw new RuntimeException();
            };
        }
    }

    public record Command (String direction, int value){
        public static Command parseString(final String input){
            final String[] s = input.split(" ");
            return new Command(s[0], Integer.parseInt(s[1]));
        }
    }


    public Position position = new Position();

    public Submarine applyCourse(final List<Command> commands){
        for (Command command : commands) {
            this.position = this.position.apply(command);
        }
        return this;
    }

    public int courseData(){
        return position.depth* position.horizontal;
    }

}
