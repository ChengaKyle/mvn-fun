package de.factorizer;

import java.util.*;

public class Factorizer {
    private static Factorizer instance;

    private Factorizer() {
        // private constructor
    }

    public static Factorizer getInstance() {
        if (instance == null) {
            instance = new Factorizer();
        }
        return instance;
    }

    public List<Integer> factorize(int n) {
        List<Integer> factors = new ArrayList<>();
        if (n < 2) return factors;

        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }

        if (n > 1) {
            factors.add(n);
        }

        return factors;
    }
}
