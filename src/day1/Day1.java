package day1;

import util.Vector;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    private static Direction direction = Direction.NORTH;
    private static Vector position = Vector.valueOf(0,0);
    private static List<Vector> visited = new ArrayList<>();
    private static boolean visitedTwice = false;
    private static Vector visitedTwiceVector;

    public static void main(String[] args) {
        try {
            String instructions = Files.lines(Paths.get(Day1.class.getResource("input.txt").toURI())).findFirst().get();
            for (String string: instructions.split(",")) {
                string = string.trim();
                direction = direction.cycle(string.charAt(0));
                int magnitude = Integer.parseInt(string.substring(1));
                visitedTwice(magnitude);
                position = position.add(direction.getVector().multiply(magnitude));
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        System.out.println("End: " + position.toString());
        System.out.println("Length: " + (Math.abs(position.getCartesianX()) + Math.abs(position.getCartesianY())));
        System.out.println("Visited Twice: " + visitedTwiceVector);
        System.out.println("Visited Twice Length: " + (Math.abs(visitedTwiceVector.getCartesianX()) + Math.abs(visitedTwiceVector.getCartesianY())));

    }

    private static void visitedTwice(int magnitude) {
        Vector vector = position;
        for (int i = 1; i <= magnitude; i++) {
            if (!visitedTwice) {
                vector = vector.add(direction.getVector().multiply(1));
                if (visited.contains(vector)) {
                    visitedTwiceVector = vector;
                    visitedTwice = true;
                }
                visited.add(vector);
            }
            System.out.println(vector);
        }
    }

    enum Direction {

        NORTH(Vector.valueOf(0,1), 0),
        EAST(Vector.valueOf(1,0), 1),
        SOUTH(Vector.valueOf(0,-1), 2),
        WEST(Vector.valueOf(-1,0), 3);

        Vector vector;
        int value;

        Direction(Vector vector, int value) {
            this.vector = vector;
            this.value = value;
        }

        int getValue() {
            return value;
        }

        Vector getVector() {
            return vector;
        }

        private static Direction getByValue(int value) {
            for (Direction direction : values()) {
               if (direction.getValue() == value)
                   return direction;
            }
            return null;
        }

       Direction cycle(char cycle) {
           if (cycle == 'R')
               return getByValue((this.getValue() + 1) > 3 ? 0 : this.getValue() + 1);
           if (cycle == 'L')
               return getByValue((this.getValue() - 1) < 0 ? 3 : this.getValue() - 1);
           return this;
       }

    }

}
