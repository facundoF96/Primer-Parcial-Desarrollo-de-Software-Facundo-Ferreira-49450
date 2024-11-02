package com.parcialDesarrollo.api_mutante.Controller;

import com.parcialDesarrollo.api_mutante.Service.MutantService;
import com.parcialDesarrollo.api_mutante.dto.DnaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MutantControllerTest {

    private MutantController mutantController;
    private MutantService mutantService;

    @BeforeEach
    void setUp() {
        mutantService = Mockito.mock(MutantService.class);
        mutantController = new MutantController(mutantService);
    }

    @Test
    void testIsMutant_ReturnsOk() {
        DnaRequest dnaRequest = new DnaRequest(new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"});
        when(mutantService.isMutant(dnaRequest)).thenReturn(true);

        ResponseEntity<String> response = mutantController.isMutant(dnaRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Es mutante", response.getBody());
    }

    @Test
    void testIsMutant_ReturnsForbidden() {
        DnaRequest dnaRequest = new DnaRequest(new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CTTCTA", "TCACTG"});
        when(mutantService.isMutant(dnaRequest)).thenReturn(false);

        ResponseEntity<String> response = mutantController.isMutant(dnaRequest);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("No es un mutante", response.getBody());
    }
}
