package de.factorizer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorizerTest {

    @Test
    @Order(100)
    void test100_factorize_regular_cases() {
        Factorizer factorizer = Factorizer.getInstance();

        // Test for n=1
        List<Integer> factors1 = factorizer.factorize(1);
        List<Integer> expected1 = Arrays.asList(); // No factors
        assertEquals(expected1, factors1);

        // Test for n=2
        List<Integer> factors2 = factorizer.factorize(2);
        List<Integer> expected2 = Arrays.asList(2); // Prime number
        assertEquals(expected2, factors2);

        // Test for n=3
        List<Integer> factors3 = factorizer.factorize(3);
        List<Integer> expected3 = Arrays.asList(3); // Prime number
        assertEquals(expected3, factors3);

        // Test for n=4
        List<Integer> factors4 = factorizer.factorize(4);
        List<Integer> expected4 = Arrays.asList(2, 2); // 2 * 2 = 4
        assertEquals(expected4, factors4);

        // Test for n=27
        List<Integer> factors5 = factorizer.factorize(27);
        List<Integer> expected5 = Arrays.asList(3, 3, 3); // 3 * 3 * 3 = 27
        assertEquals(expected5, factors5);

        // Test for n=65536
        List<Integer> factors6 = factorizer.factorize(65536);
        List<Integer> expected6 = Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2); // 2^16 = 65536
        assertEquals(expected6, factors6);

        // Test for n=10952347
        List<Integer> factors7 = factorizer.factorize(10952347);
        List<Integer> expected7 = Arrays.asList(7, 23, 59, 1153); // Prime factorization
        assertEquals(expected7, factors7);

        // Test for n=100000039
        List<Integer> factors8 = factorizer.factorize(100000039);
        List<Integer> expected8 = Arrays.asList(100000039); // Prime number
        assertEquals(expected8, factors8);
    }

    /**
     * Test valid corner cases: n=0, n=2147483646 (MAX_INT-1), n=2147483647 (MAX_INT).
     */
    @Test
    @Order(200)
    void test200_factorize_corner_cases() {
        Factorizer factorizer = Factorizer.getInstance();

        // Test for n=0, expecting IllegalArgumentException
        IllegalArgumentException thrown0 = assertThrows(IllegalArgumentException.class, () -> {
            factorizer.factorize(0);
        });
        assertEquals("Input must be a positive integer greater than 0", thrown0.getMessage());

        // Test for n=2147483646 (MAX_INT-1), expecting a valid prime factorization
        List<Integer> factors1 = factorizer.factorize(2147483646);
        List<Integer> expected1 = Arrays.asList(2, 3, 3, 7, 11, 31, 151, 331); // Correct prime factors of 2147483646
        assertEquals(expected1, factors1);

        // Test for n=2147483647 (MAX_INT), expecting the number itself since it's prime
        List<Integer> factors2 = factorizer.factorize(2147483647);
        List<Integer> expected2 = Arrays.asList(2147483647); // Prime number (itself)
        assertEquals(expected2, factors2);
    }


    @Test
    @Order(300)
    void test300_factorize_exception_cases() {
        Factorizer factorizer = Factorizer.getInstance();

        // Test for n=-1
        IllegalArgumentException thrown1 = assertThrows(IllegalArgumentException.class, () -> {
            factorizer.factorize(-1);
        });
        assertEquals("Input must be a positive integer greater than 0", thrown1.getMessage());

        // Test for n=-10
        IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class, () -> {
            factorizer.factorize(-10);
        });
        assertEquals("Input must be a positive integer greater than 0", thrown2.getMessage());

        // Test for n=-2147483648 (MIN_INT)
        IllegalArgumentException thrown3 = assertThrows(IllegalArgumentException.class, () -> {
            factorizer.factorize(-2147483648);
        });
        assertEquals("Input must be a positive integer greater than 0", thrown3.getMessage());
    }

}
