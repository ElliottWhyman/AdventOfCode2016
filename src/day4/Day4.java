package day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// Holy shit this was messy but hella fun
public class Day4 {

    private static int idSum = 0;


    public static void main(String[] args) {
        try {
            Iterator<String> iterator = Files.lines(Paths.get(Day4.class.getResource("input.txt").toURI())).iterator();
            while (iterator.hasNext()) {
                String code = iterator.next();
                decryptCodes(code);
                String[] strings = code.split("-");
                List<Character> characters = new ArrayList<>();
                for (int i = 0; i < strings.length - 1; i++) {
                    for (int j = 0; j < strings[i].length(); j++) {
                        characters.add(strings[i].charAt(j));
                    }
                }

                TreeMap<Character, Long> sorted = new TreeMap<>(characters.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
                List<Map.Entry<Character, Long>> result = sorted.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(5)
                        .collect(Collectors.toList());
                String checksum = "";
                for (Map.Entry<Character, Long> map : result) {
                    checksum += map.getKey();
                }

                if (checksum.equals(strings[strings.length-1].substring(4,9))) {
                    idSum += Integer.parseInt(strings[strings.length-1].substring(0,3));
                }
            }
            System.out.println(idSum);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    // Part Two
    private static void decryptCodes(String code) {
        String[] strings = code.split("-");
        System.out.print(strings[strings.length-1].substring(0,3) + " ");
        for (char c : String.join(" ", Arrays.copyOf(strings, strings.length-1)).toCharArray())
            if (c == ' ')
                System.out.print(" ");
            else System.out.print(decrypt(c, Integer.parseInt(strings[strings.length-1].substring(0,3))));
        System.out.println();
    }

    private static char decrypt(char character, int id) {
        int amount = id % 26;
        int a = 'z' - character;
        if (a > amount)
            return (char)(character + amount);

        return (char)(('a' + (amount - a))-1);
    }

}
