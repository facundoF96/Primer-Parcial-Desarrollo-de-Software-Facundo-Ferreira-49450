package com.parcialDesarrollo.api_mutante.Service;

import com.parcialDesarrollo.api_mutante.Entities.Dna;
import com.parcialDesarrollo.api_mutante.dto.DnaRequest;
import com.parcialDesarrollo.api_mutante.dto.DnaResult;
import com.parcialDesarrollo.api_mutante.dto.StatsResponse;
import com.parcialDesarrollo.api_mutante.Repository.DnaRepository;
import com.parcialDesarrollo.api_mutante.Repository.MutantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MutantServiceTest {

    private MutantService mutantService;
    private DnaRepository dnaRepository;
    private MutantRepository mutantRepository;

    @BeforeEach
    public void setUp() {
        dnaRepository = Mockito.mock(DnaRepository.class);
        mutantRepository = Mockito.mock(MutantRepository.class);
        mutantService = new MutantService(dnaRepository, mutantRepository);
    }

    @Test
    public void testIsMutantWithNullDna() {
        DnaRequest request = new DnaRequest(null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            mutantService.isMutant(request);
        });
        assertEquals("No puede ser nulo ni estar vacio", exception.getMessage());
    }

    @Test
    public void testIsMutantWithEmptyDna() {
        DnaRequest request = new DnaRequest(new String[]{});
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            mutantService.isMutant(request);
        });
        assertEquals("No puede ser nulo ni estar vacio", exception.getMessage());
    }

    @Test
    public void testIsMutantWithMutantDna() {
        DnaRequest request = new DnaRequest(new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"});
        when(dnaRepository.findByDna(anyString())).thenReturn(Optional.empty());
        boolean result = mutantService.isMutant(request);
        assertTrue(result);
        verify(dnaRepository).save(any(Dna.class)); // Verifica que se guarda en la base de datos
    }

    @Test
    public void testIsMutantWithExistingMutantDna() {
        String existingDnaString = "ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG";
        Dna existingDna = new Dna();
        existingDna.setMutant(true);
        when(dnaRepository.findByDna(existingDnaString)).thenReturn(Optional.of(existingDna));

        DnaRequest request = new DnaRequest(new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"});
        boolean result = mutantService.isMutant(request);
        assertTrue(result); // Debería devolver verdadero porque el ADN ya existe como mutante
    }

    @Test
    public void testGetStats() {
        when(mutantRepository.countByIsMutantTrue()).thenReturn(10L);
        when(mutantRepository.countByIsMutantFalse()).thenReturn(5L);

        StatsResponse stats = mutantService.getStats();

        assertEquals(10, stats.getCountMutantDna());
        assertEquals(5, stats.getCountHumanDna());
        assertEquals(2.0, stats.getRatio()); // 10/5 = 2
    }

    @Test
    public void testGetStatsWithNoHumans() {
        when(mutantRepository.countByIsMutantTrue()).thenReturn(10L);
        when(mutantRepository.countByIsMutantFalse()).thenReturn(0L);

        StatsResponse stats = mutantService.getStats();

        assertEquals(10, stats.getCountMutantDna());
        assertEquals(0, stats.getCountHumanDna());
        assertEquals(0, stats.getRatio()); // División por cero
    }

    @Test
    public void testGetStatsWithNoMutants() {
        when(mutantRepository.countByIsMutantTrue()).thenReturn(0L);
        when(mutantRepository.countByIsMutantFalse()).thenReturn(5L);

        StatsResponse stats = mutantService.getStats();

        assertEquals(0, stats.getCountMutantDna());
        assertEquals(5, stats.getCountHumanDna());
        assertEquals(0.0, stats.getRatio()); //
    }

    @Test
    public void testVerificarSecuenciaWithMixedSequences() {
        String[] dna = new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        boolean result = mutantService.verificarSecuencia(dna);
        assertTrue(result); // Debería encontrar secuencias
    }

    @Test
    public void testVerificarSecuenciaWithCornerCase() {
        String[] dna = new String[]{"AAAA", "ATGC", "ATGC", "ATGC"};
        boolean result = mutantService.verificarSecuencia(dna);
        assertTrue(result); // Debería encontrar una secuencia horizontal AAAA
    }

    @Test
    public void testIsMutantWithMultipleMutantSequences() {
        DnaRequest request = new DnaRequest(new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"});
        when(dnaRepository.findByDna(anyString())).thenReturn(Optional.empty());
        boolean result = mutantService.isMutant(request);
        assertTrue(result); // Debería devolver verdadero por tener múltiples secuencias
    }

    @Test
    public void testIsMutantWithDnaOfDifferentLengths() {
        DnaRequest request = new DnaRequest(new String[]{"ATG", "AAA", "TAC"});
        boolean result = mutantService.isMutant(request);
        assertFalse(result); // No debe ser mutante
    }

    @Test
    public void testIsMutantWithOnlyMutantSequences() {
        String[] dna = new String[]{
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        }; // Todos son mutantes
        DnaRequest request = new DnaRequest(dna);
        when(dnaRepository.findByDna(anyString())).thenReturn(Optional.empty());
        boolean result = mutantService.isMutant(request);
        assertTrue(result); // Debería ser un mutante
    }

    @Test
    public void testIsMutantWithComplexDiagonal() {
        String[] dna = new String[]{
                "ATGCGC",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        }; // Contiene múltiples secuencias mutantes
        DnaRequest request = new DnaRequest(dna);
        when(dnaRepository.findByDna(anyString())).thenReturn(Optional.empty());
        boolean result = mutantService.isMutant(request);
        assertTrue(result); // Debería ser un mutante
    }

    @Test
    public void testVerificarSecuenciaWithAllUnique() {
        String[] dna = new String[]{"ATG", "CGC", "TAC"};
        boolean result = mutantService.verificarSecuencia(dna);
        assertFalse(result); // No debería encontrar secuencias mutantes
    }
}
