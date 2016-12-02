package day2;

import util.Vector;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;

public class Day2 {

    private static HashMap<Vector, Character> keypad1 = new HashMap<>();
    private static HashMap<Vector, Character> keypad2 = new HashMap<>();
    private static Vector location1 = Vector.valueOf(0,0);
    private static Vector location2 = Vector.valueOf(0,0);
    private static String code1 = "";
    private static String code2 = "";

    public static void main(String[] args) {
        populateKeypad();
        try {
            Iterator<String> iterator = Files.lines(Paths.get(Day2.class.getResource("input.txt").toURI())).iterator();
            while (iterator.hasNext()) {
                String string = iterator.next();
                for (int i = 0; i < string.length(); i++) {
                    location1 = evaluatePosition(Instruction.parseInstruction(string.charAt(i)).getVector(), keypad1, location1);
                    location2 = evaluatePosition(Instruction.parseInstruction(string.charAt(i)).getVector(), keypad2, location2);
                }
                code1 += keypad1.get(location1);
                code2 += keypad2.get(location2);
            }
            System.out.println(code1);
            System.out.println(code2);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void populateKeypad() {
        keypad1.put(Vector.valueOf(-1,1), '1');
        keypad1.put(Vector.valueOf(0,1), '2');
        keypad1.put(Vector.valueOf(1,1), '3');
        keypad1.put(Vector.valueOf(-1,0),'4');
        keypad1.put(Vector.valueOf(0,0), '5');
        keypad1.put(Vector.valueOf(1,0), '6');
        keypad1.put(Vector.valueOf(-1,-1), '7');
        keypad1.put(Vector.valueOf(0,-1), '8');
        keypad1.put(Vector.valueOf(1,-1), '9');

        keypad2.put(Vector.valueOf(0,0),'5');
        keypad2.put(Vector.valueOf(1,0),'6');
        keypad2.put(Vector.valueOf(1,1),'2');
        keypad2.put(Vector.valueOf(1,-1),'A');
        keypad2.put(Vector.valueOf(2,0),'7');
        keypad2.put(Vector.valueOf(2,1),'3');
        keypad2.put(Vector.valueOf(2,2),'1');
        keypad2.put(Vector.valueOf(2,-1),'B');
        keypad2.put(Vector.valueOf(2,-2),'D');
        keypad2.put(Vector.valueOf(3,0),'8');
        keypad2.put(Vector.valueOf(3,1),'4');
        keypad2.put(Vector.valueOf(3,-1),'C');
        keypad2.put(Vector.valueOf(4,0),'9');
    }

    private static Vector evaluatePosition(Vector instruction, HashMap<Vector, Character> keypad, Vector location) {
        Vector vector = location.add(instruction);
        if (!keypad.containsKey(vector))
            vector = location;
        return vector;
    }

    enum Instruction {

        UP(Vector.valueOf(0,1)),
        DOWN(Vector.valueOf(0,-1)),
        LEFT(Vector.valueOf(-1,0)),
        RIGHT(Vector.valueOf(1,0)),
        ZERO(Vector.valueOf(0,0));

        Vector vector;

        Instruction(Vector vector) {
            this.vector = vector;
        }

        public Vector getVector() {
            return this.vector;
        }

        public static Instruction parseInstruction(char instruction) {
            switch (instruction) {
                case 'U': return UP;
                case 'D': return DOWN;
                case 'L': return LEFT;
                case 'R': return RIGHT;
            }
            return ZERO;
        }
    }

}
