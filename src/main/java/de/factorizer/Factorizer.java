package de.factorizer;

import java.util.ArrayList;
import java.util.List;

public class Factorizer {

    private static Factorizer instance;

    // Private constructor to prevent instantiation
    private Factorizer() {}

    // Lazy Singleton
    public static synchronized Factorizer getInstance() {
        if (instance == null) {
            instance = new Factorizer();
        }
        return instance;
    }

    /**
     * Factorizes a number into prime factors.
     *
     * @param n the number to be factorized
     * @return a list of prime factors
     * @throws IllegalArgumentException if n <= 0
     */
    public List<Integer> factorize(int n) {
        // Throw exception if n <= 0
        if (n <= 0) {
            throw new IllegalArgumentException("Input must be a positive integer greater than 0");
        }

        List<Integer> factors = new ArrayList<>();

        // Trial division for prime factorization
        for (int i = 2; i <= Math.sqrt(n); i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }

        // If n > 1, it is a prime number
        if (n > 1) {
            factors.add(n);
        }

        return factors;
    }
}

