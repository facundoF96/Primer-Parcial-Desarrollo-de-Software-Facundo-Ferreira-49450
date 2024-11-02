package com.parcialDesarrollo.api_mutante.dto;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class DnaResultTest {

    @Test
    public void testDnaResultConstructor() {
        List<String> sequences = Arrays.asList("ATGCGA", "CAGTGC");
        DnaResult dnaResult = new DnaResult(sequences, true);

        assertEquals(sequences, dnaResult.getDnaSequences());
        assertTrue(dnaResult.isMutant());
    }

    @Test
    public void testDnaResultDefaultConstructor() {
        DnaResult dnaResult = new DnaResult();

        assertNull(dnaResult.getDnaSequences());
        assertFalse(dnaResult.isMutant());
    }

    @Test
    public void testSetDnaSequences() {
        DnaResult dnaResult = new DnaResult();
        List<String> sequences = Arrays.asList("ATGCGA", "CAGTGC");
        dnaResult.setDnaSequences(sequences);

        assertEquals(sequences, dnaResult.getDnaSequences());
    }

    @Test
    public void testSetIsMutant() {
        DnaResult dnaResult = new DnaResult();
        dnaResult.setMutant(true);

        assertTrue(dnaResult.isMutant());
    }

    @Test
    public void testDnaResultConstructorAndGetter() {
        List<String> dnaSequences = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT");
        boolean isMutant = true;
        DnaResult result = new DnaResult(dnaSequences, isMutant);
        assertEquals(dnaSequences, result.getDnaSequences());
        assertTrue(result.isMutant());
    }

    @Test
    public void testDnaResultSetter() {
        DnaResult result = new DnaResult();
        result.setDnaSequences(Arrays.asList("CCCCTA", "TCACTG"));
        result.setMutant(false);
        assertEquals(Arrays.asList("CCCCTA", "TCACTG"), result.getDnaSequences());
        assertFalse(result.isMutant());
    }

    @Test
    public void testDnaResultEquality() {
        List<String> dnaSequences = Arrays.asList("ATGCGA", "CAGTGC");
        DnaResult result1 = new DnaResult(dnaSequences, true);
        DnaResult result2 = new DnaResult(dnaSequences, true);
        assertEquals(result1, result2);
    }

    @Test
    public void testDnaResultNotEqual() {
        DnaResult result1 = new DnaResult(Arrays.asList("ATGCGA"), true);
        DnaResult result2 = new DnaResult(Arrays.asList("CAGTGC"), false);
        assertNotEquals(result1, result2);
    }

    @Test
    public void testEmptyDnaSequencesList() {
        List<String> dnaSequences = Collections.emptyList();
        DnaResult result = new DnaResult(dnaSequences, true);
        assertEquals(dnaSequences, result.getDnaSequences());
        assertTrue(result.isMutant());
    }

    @Test
    public void testDnaSequencesListWithNullValues() {
        List<String> dnaSequences = Arrays.asList(null, "CAGTGC");
        DnaResult result = new DnaResult(dnaSequences, false);
        assertEquals(dnaSequences, result.getDnaSequences());
        assertFalse(result.isMutant());
    }
}

