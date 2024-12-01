package dev.zlabi.advent_of_code.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Day01SolutionTwo {

    public static void main(String[] args) {
        String filePath = "day01/input.txt";

        readFileFromResources(filePath);
    }

    public static void readFileFromResources(String resourcePath) {
        // Use the class loader to load the resource
        try (InputStream inputStream = Day01SolutionTwo.class.getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String line;
            List<Long> leftSide = new ArrayList<>();
            List<Long> rightSide = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String left = line.split(" {3}")[0];
                String right = line.split(" {3}")[1];

                long leftNumber = Long.parseLong(left);
                long rightNumber = Long.parseLong(right);
                leftSide.add(leftNumber);
                rightSide.add(rightNumber);
            }
            leftSide.sort(Comparator.comparing(Long::intValue));
            rightSide.sort(Comparator.comparing(Long::intValue));

            long sum = 0;
            for (Long searchNumber : leftSide) {
                long sumRight = rightSide.stream().filter(r -> r.equals(searchNumber)).count();

                sum += searchNumber * sumRight;
            }

            System.out.println("Sum: " + sum);
        } catch (IOException e) {
            // Handle exceptions
            System.err.println("Error reading the resource: " + e.getMessage());
        }
    }
}
