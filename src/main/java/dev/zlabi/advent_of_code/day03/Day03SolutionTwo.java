package dev.zlabi.advent_of_code.day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03SolutionTwo {

    public static void main(String[] args) {
        String filePath = "day03/input.txt";

        readFileFromResources(filePath);
    }

    public static void readFileFromResources(String resourcePath) {
        // Use the class loader to load the resource
        try (InputStream inputStream = Day03SolutionTwo.class.getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String line;

            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {

                sb.append(line);
            }
            int sum = calculateSum(sb.toString());

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

    public static int calculateSum(String input) {
        // Regex to extract all relevant instructions
        String regex = "do\\(\\)|don't\\(\\)|mul\\(\\d+,\\d+\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // List to hold the extracted instructions
        List<String> instructions = new ArrayList<>();

        while (matcher.find()) {
            instructions.add(matcher.group());
        }

        // Initial state: mul instructions are enabled
        boolean isMulEnabled = true;
        int sum = 0;

        // Process each instruction
        for (String instruction : instructions) {
            if (instruction.equals("do()")) {
                isMulEnabled = true;
            } else if (instruction.equals("don't()")) {
                isMulEnabled = false;
            } else if (instruction.startsWith("mul(") && isMulEnabled) {
                // Extract numbers from mul(x, y)
                String[] numbers = instruction.substring(4, instruction.length() - 1).split(",");
                int x = Integer.parseInt(numbers[0]);
                int y = Integer.parseInt(numbers[1]);
                sum += x * y; // Multiply and add to sum
            }
        }

        return sum;
    }
}
