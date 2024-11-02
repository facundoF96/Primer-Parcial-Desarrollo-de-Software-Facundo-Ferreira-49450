package com.parcialDesarrollo.api_mutante.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DnaTest {

    private Dna dna;

    @BeforeEach
    void setUp() {
        dna = Dna.builder()
                .id(1L)
                .dna("ATGCGA")
                .isMutant(true)
                .build();
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1L, dna.getId());
        assertEquals("ATGCGA", dna.getDna());
        assertTrue(dna.isMutant());
    }

    @Test
    void testEqualsAndHashCode() {
        Dna anotherDna = Dna.builder()
                .id(1L)
                .dna("ATGCGA")
                .isMutant(true)
                .build();

        assertEquals(dna, anotherDna);
        assertEquals(dna.hashCode(), anotherDna.hashCode());
    }

}
