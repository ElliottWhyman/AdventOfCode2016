package day3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day3 {

    private static int possible = 0;
    private static List<String[]> allTriangles = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Iterator<String> iterator = Files.lines(Paths.get(Day3.class.getResource("input.txt").toURI())).iterator();
            while (iterator.hasNext()) {
                String[] string = iterator.next().trim().split(" +");
                allTriangles.add(string);
                if (isTriangle(Integer.parseInt(string[0]), Integer.parseInt(string[1]), Integer.parseInt(string[2])))
                    possible++;

            }
            System.out.println("Part 1: " + possible);
            System.out.println("Part 2: " + partTwo());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static int partTwo() {
        int possible = 0;
        for (int i = 0; i < allTriangles.size(); i+=3) {
            for (int j = 0; j < 3; j++) {
                System.out.println(allTriangles.get(i)[j]  + " " + allTriangles.get(i + 1)[j] + " " + allTriangles.get(i + 2)[j]);
                if (isTriangle(Integer.parseInt(allTriangles.get(i)[j]), Integer.parseInt(allTriangles.get(i + 1)[j]), Integer.parseInt(allTriangles.get(i + 2)[j])))
                    possible++;
            }
        }
        return possible;
    }

    private static boolean isTriangle(int a, int b, int c) {
        return (a+b > c) && (a+c > b) && (b+c > a);
    }
}
