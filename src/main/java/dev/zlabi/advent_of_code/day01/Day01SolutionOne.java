package dev.zlabi.advent_of_code.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Day01SolutionOne {

    public static void main(String[] args) {
        String filePath = "day01/input.txt";

        readFileFromResources(filePath);
    }

    public static void readFileFromResources(String resourcePath) {
        // Use the class loader to load the resource
        try (InputStream inputStream = Day01SolutionOne.class.getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String line;
            List<Integer> leftSide = new ArrayList<>();
            List<Integer> rightSide = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String left = line.split(" {3}")[0];
                String right = line.split(" {3}")[1];

                int leftNumber = Integer.parseInt(left);
                int rightNumber = Integer.parseInt(right);
                leftSide.add(leftNumber);
                rightSide.add(rightNumber);
            }
            leftSide.sort(Comparator.comparing(Integer::intValue));
            rightSide.sort(Comparator.comparing(Integer::intValue));

            int sum = 0;
            for (int i = 0; i < leftSide.size(); i++) {
                Integer le = leftSide.get(i);
                Integer re = rightSide.get(i);

                if (le.equals(re)) {
                    continue;
                }
                if (le > re) {
                    sum += le - re;
                }
                if (le < re) {
                    sum += re - le;
                }
            }

            System.out.println("Sum: " + sum);
        } catch (IOException e) {
            // Handle exceptions
            System.err.println("Error reading the resource: " + e.getMessage());
        }
    }
}
