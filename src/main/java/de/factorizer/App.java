package de.factorizer;

import java.util.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello Factors!");
        Factorizer factorizer = Factorizer.getInstance();

        for (String arg : args) {
            try {
                int n = Integer.parseInt(arg);
                List<Integer> factors = factorizer.factorize(n);
                System.out.print(" - n=" + n + " -> " + factors);
                if (factors.size() == 1 && factors.get(0) == n) {
                    System.out.print(" (prime number)");
                }
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + arg);
            }
        }
    }
}
