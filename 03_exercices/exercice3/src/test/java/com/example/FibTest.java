package com.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FibTest {

    @Test
    void getFibSeries_range1_shouldNotBeEmptyAndContainZero() {
        Fib fib = new Fib(1);

        List<Integer> result = fib.getFibSeries();

        assertFalse(result.isEmpty());
        assertEquals(List.of(0), result);
    }

    @Test
    void getFibSeries_range6_shouldMatchAllScenarios() {
        Fib fib = new Fib(6);

        List<Integer> result = fib.getFibSeries();

        assertTrue(result.contains(3));
        assertEquals(6, result.size());
        assertFalse(result.contains(4));
        assertEquals(List.of(0, 1, 1, 2, 3, 5), result);

        for (int i = 0; i < result.size() - 1; i++) {
            assertTrue(result.get(i) <= result.get(i + 1));
        }
    }
}
