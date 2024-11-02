package com.parcialDesarrollo.api_mutante.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DnaRequestTest {

    @Test
    public void testDnaRequestConstructor() {
        String[] dna = new String[]{"ATGCGA", "CAGTGC"};
        DnaRequest dnaRequest = new DnaRequest(dna);

        assertArrayEquals(dna, dnaRequest.getDna());
    }

    @Test
    public void testDnaRequestDefaultConstructor() {
        DnaRequest dnaRequest = new DnaRequest();

        assertNull(dnaRequest.getDna());
    }

    @Test
    public void testSetDna() {
        DnaRequest dnaRequest = new DnaRequest();
        String[] dna = new String[]{"ATGCGA", "CAGTGC"};
        dnaRequest.setDna(dna);

        assertArrayEquals(dna, dnaRequest.getDna());
    }

    @Test
    public void testDnaRequestConstructorAndGetter() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        DnaRequest request = new DnaRequest(dna);
        assertArrayEquals(dna, request.getDna());
    }

    @Test
    public void testDnaRequestSetter() {
        String[] dna = {"ATGCGA", "CAGTGC"};
        DnaRequest request = new DnaRequest();
        request.setDna(dna);
        assertArrayEquals(dna, request.getDna());
    }

    @Test
    public void testDnaRequestEquality() {
        String[] dna = {"ATGCGA", "CAGTGC"};
        DnaRequest request1 = new DnaRequest(dna);
        DnaRequest request2 = new DnaRequest(dna);
        assertEquals(request1, request2);
    }

    @Test
    public void testDnaRequestNotEqual() {
        DnaRequest request1 = new DnaRequest(new String[]{"ATGCGA"});
        DnaRequest request2 = new DnaRequest(new String[]{"CAGTGC"});
        assertNotEquals(request1, request2);
    }

    @Test
    public void testEmptyDnaArray() {
        String[] dna = {};
        DnaRequest request = new DnaRequest(dna);
        assertArrayEquals(dna, request.getDna());
    }

    @Test
    public void testDnaArrayWithNullValues() {
        String[] dna = {null, "CAGTGC"};
        DnaRequest request = new DnaRequest(dna);
        assertArrayEquals(dna, request.getDna());
    }
}
