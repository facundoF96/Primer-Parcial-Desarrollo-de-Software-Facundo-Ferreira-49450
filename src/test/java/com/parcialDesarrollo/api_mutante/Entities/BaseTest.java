package com.parcialDesarrollo.api_mutante.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseTest {

    @Test
    void testConstructorAndGetId() {
        Base base = new Base(1L);
        assertEquals(1L, base.getId());
    }

    @Test
    void testSetId() {
        Base base = new Base();
        base.setId(2L);
        assertEquals(2L, base.getId());
    }

    @Test
    void testEqualsAndHashCode() {
        Base base1 = new Base(1L);
        Base base2 = new Base(1L);
        assertEquals(base1, base2);
        assertEquals(base1.hashCode(), base2.hashCode());
    }

    @Test
    void testToString() {
        Base base = new Base(1L);
        String toStringResult = base.toString();
        assertTrue(toStringResult.contains("Base"));
        assertTrue(toStringResult.contains("id=1"));
    }

    @Test
    void testBaseConstructor() {
        Base base = new Base(1L);
        assertNotNull(base);
        assertEquals(1L, base.getId());
    }

    @Test
    void testBaseEquality() {
        Base base1 = new Base(1L);
        Base base2 = new Base(1L);
        assertEquals(base1, base2);
    }
}

