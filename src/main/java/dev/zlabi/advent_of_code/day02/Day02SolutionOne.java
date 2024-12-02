package dev.zlabi.advent_of_code.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day02SolutionOne {

    public static void main(String[] args) {
        String filePath = "day02/input.txt";

        readFileFromResources(filePath);
    }

    public static void readFileFromResources(String resourcePath) {
        // Use the class loader to load the resource
        try (InputStream inputStream = Day02SolutionOne.class.getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String line;

            long lineNumer = 1;
            long sum = 0;
            while ((line = br.readLine()) != null) {
                List<Long> lineList = Arrays.stream(line.split(" ")).map(Long::parseLong).collect(Collectors.toList());
                if (isLineSafe(lineList)) {
                    sum++;
                }
                System.out.println("" + lineNumer + isLineSafe(lineList));
                lineNumer++;
            }
            System.out.println("Sum: " + sum);
        } catch (IOException e) {
            // Handle exceptions
            System.err.println("Error reading the resource: " + e.getMessage());
        }
    }

    private static boolean isLineSafe(List<Long> lineList) {

        boolean lineSave = false;
        boolean lineIsDecreasing = false;
        boolean lineIsIncreasing = false;
        for (int i = 0; i < lineList.size() - 1; i++) {
            if (lineList.get(i).equals(lineList.get(i + 1))) {
                lineSave = false;
            }
            if (lineList.get(i) > lineList.get(i + 1)) {
                long diff = lineList.get(i) - lineList.get(i + 1);
                lineIsDecreasing = true;
                lineSave = diff <= 3L;
            }
            if (lineList.get(i) < lineList.get(i + 1)) {
                long diff = lineList.get(i + 1) - lineList.get(i);
                lineIsIncreasing = true;
                lineSave = diff <= 3L;
            }
            if (!lineSave || (lineIsDecreasing && lineIsIncreasing)) {
                return false;
            }
        }

        return true;
    }
}
