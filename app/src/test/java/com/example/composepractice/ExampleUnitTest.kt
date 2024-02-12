package com.example.composepractice

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun calculate_algorithm_when_n_equal_7_and_k_equal_3() {
        val actual = calculateAlgorithm(7, 3)
        assertEquals(4, actual.save)
        assertEquals(listOf(3, 6, 2, 7, 5, 1), actual.died)
    }

    @Test
    fun calculate_algorithm_when_n_equal_15_and_k_equal_4() {
        val actual = calculateAlgorithm(15, 4)
        assertEquals(13, actual.save)
    }
}