package kavorka.dndspelltracker

import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun ability10ModTest() {
        assertEquals(0, getAbilityMod(10))
    }

    @Test
    fun ability11ModTest() {
        assertEquals(0, getAbilityMod(11))
    }

    @Test
    fun ability12ModTest() {
        assertEquals(1, getAbilityMod(12))
    }

    @Test
    fun ability13ModTest() {
        assertEquals(1, getAbilityMod(13))
    }

    @Test
    fun ability14ModTest() {
        assertEquals(2, getAbilityMod(14))
    }

    @Test
    fun ability15ModTest() {
        assertEquals(2, getAbilityMod(15))
    }

    @Test
    fun ability30ModTest() {
        assertEquals(10, getAbilityMod(30))
    }

    @Test
    fun ability9ModTest() {
        assertEquals(-1, getAbilityMod(9))
    }

    @Test
    fun ability8ModTest() {
        assertEquals(-1, getAbilityMod(8))
    }

    @Test
    fun ability1ModTest() {
        assertEquals(-5, getAbilityMod(1))
    }
}