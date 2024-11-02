package com.parcialDesarrollo.api_mutante.Service;

import com.parcialDesarrollo.api_mutante.Entities.Dna;
import com.parcialDesarrollo.api_mutante.Repository.DnaRepository;
import com.parcialDesarrollo.api_mutante.dto.DnaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class MutantServiceTest {

    @Mock
    private DnaRepository dnaRepository;

    @InjectMocks
    private MutantService mutantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsMutant_ValidMutantDna_ReturnsTrue() {
        DnaRequest mutantDnaRequest = new DnaRequest(new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        });

        when(dnaRepository.findByDna("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG"))
                .thenReturn(Optional.empty());

        boolean isMutant = mutantService.isMutant(mutantDnaRequest);
        assertTrue(isMutant);
    }

    @Test
    public void testIsMutant_ValidHumanDna_ReturnsFalse() {
        DnaRequest humanDnaRequest = new DnaRequest(new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATTT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        });

        when(dnaRepository.findByDna("ATGCGA,CAGTGC,TTATTT,AGACGG,GCGTCA,TCACTG"))
                .thenReturn(Optional.empty());

        boolean isMutant = mutantService.isMutant(humanDnaRequest);
        assertFalse(isMutant);
    }

    @Test
    public void testIsMutant_DnaAlreadyExistsInDatabase() {
        DnaRequest dnaRequest = new DnaRequest(new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        });

        Dna existingDna = new Dna();
        existingDna.setDna("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG");
        existingDna.setMutant(true);

        when(dnaRepository.findByDna("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG"))
                .thenReturn(Optional.of(existingDna));

        boolean isMutant = mutantService.isMutant(dnaRequest);
        assertTrue(isMutant);
    }
}


