package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;

public class Solve {

    private static Solve solveInstance;
    public ArrayList<Double> numbers;

    private Solve() {
        this.numbers = new ArrayList<>();
    }

    public static Solve getInstance() {
        if (solveInstance == null) {
            solveInstance = new Solve();
        }
        return solveInstance;
    }

    public void readTxt(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                double number = Integer.parseInt(line.trim());
                numbers.add(number);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double maxNum() {
        return Collections.max(numbers);
    }

    public double minNum() {
        return Collections.min(numbers);
    }

    public double median() {
        int listSize = numbers.size();
        if(listSize % 2 == 0) return ((numbers.get(listSize / 2) - 1) + numbers.get(listSize / 2)) / 2;
        else return numbers.get((listSize - 1) / 2);
    }

    public double avg() {
        Double sum = 0d;
        if(!numbers.isEmpty()) {
            for (Double mark : numbers) {
                sum += mark;
            }
            return sum / numbers.size();
        }
        return sum;
    }

    public int upwardCombo() {
        int combo = 1;
        int longestCombo = 1;
        Double prev = null; // Use null to indicate that prev is uninitialized

        for (Double current : numbers) {
            if (prev != null && current > prev) {
                combo++;
                if (combo > longestCombo) {
                    longestCombo = combo;
                }
            } else {
                combo = 1; // Reset combo to 1 if the current number is not greater than the previous one
            }
            prev = current;
        }

        return longestCombo;
    }

    public int downwardCombo() {
        int combo = 1;
        int longestCombo = 1;
        Double prev = null; // Use null to indicate that prev is uninitialized

        for (Double current : numbers) {
            if (prev != null && current < prev) {
                combo++;
                if (combo > longestCombo) {
                    longestCombo = combo;
                }
            } else {
                combo = 1; // Reset combo to 1 if the current number is not greater than the previous one
            }
            prev = current;
        }

        return longestCombo;
    }
}

