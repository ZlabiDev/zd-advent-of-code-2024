package dev.zlabi.advent_of_code.day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03SolutionOne {

    public static void main(String[] args) {
        String filePath = "day03/input.txt";

        readFileFromResources(filePath);
    }

    public static void readFileFromResources(String resourcePath) {
        // Use the class loader to load the resource
        try (InputStream inputStream = Day03SolutionOne.class.getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String line;

            long lineNumer = 1;
            long sum = 0;
            while ((line = br.readLine()) != null) {

                String[] split = extractValidPairs(line).split("\\)");
                for (String pair : split) {

//                }
//                Arrays.stream().forEach(pair -> {
                    String first = pair.substring(4, pair.indexOf(','));
                    System.out.println("FIRST: " + first);

                    String second = pair.substring(pair.indexOf(',') + 1, pair.length());
                    System.out.println("SECOND: " + second);

                    sum += Long.parseLong(first) * Long.parseLong(second);
                }
//                System.out.println();
            }
            System.out.println("Sum: " + sum);
        } catch (IOException e) {
            // Handle exceptions
            System.err.println("Error reading the resource: " + e.getMessage());
        }
    }

    public static String extractValidPairs(String input) {
        if (input == null) {
            return null; // Handle null input
        }

        // Regex to find patterns like (number,number)
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Matcher matcher = pattern.matcher(input);

        StringBuilder result = new StringBuilder();

        // Append all matches to the result
        while (matcher.find()) {
            result.append(matcher.group());
        }

        return result.toString();
    }
}
