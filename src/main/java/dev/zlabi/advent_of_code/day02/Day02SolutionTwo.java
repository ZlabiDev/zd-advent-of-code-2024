package dev.zlabi.advent_of_code.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day02SolutionTwo {

    public static void main(String[] args) {
        String filePath = "day02/input.txt";

        readFileFromResources(filePath);
    }

    public static void readFileFromResources(String resourcePath) {
        try (InputStream inputStream = Day02SolutionTwo.class.getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String line;
            long lineNumber = 1;
            long safeCount = 0;

            while ((line = br.readLine()) != null) {
                List<Long> levels = Arrays.stream(line.split(" "))
                        .map(Long::parseLong)
                        .collect(Collectors.toList());

                boolean isSafe = isLineSafe(levels) || canBeMadeSafe(levels);

                if (isSafe) {
                    safeCount++;
                }

                System.out.printf("Line %d: %s -> Safe: %b%n", lineNumber, levels, isSafe);
                lineNumber++;
            }

            System.out.println("Total safe reports: " + safeCount);
        } catch (IOException e) {
            System.err.println("Error reading the resource: " + e.getMessage());
        }
    }

    private static boolean isLineSafe(List<Long> levels) {
        if (levels.size() < 2) {
            return false;
        }

        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 0; i < levels.size() - 1; i++) {
            long diff = levels.get(i + 1) - levels.get(i);

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false; // Difference out of range
            }

            if (diff > 0) {
                isDecreasing = false;
            } else if (diff < 0) {
                isIncreasing = false;
            }
        }

        return isIncreasing || isDecreasing; // All increasing or all decreasing
    }

    private static boolean canBeMadeSafe(List<Long> levels) {
        for (int i = 0; i < levels.size(); i++) {
            // Create a modified list by excluding the element at index `i`
            List<Long> modifiedLevels = new ArrayList<>(levels);
            modifiedLevels.remove(i);

            if (isLineSafe(modifiedLevels)) {
                return true; // If removing one level makes it safe
            }
        }
        return false; // Cannot be made safe by removing any single level
    }
}
