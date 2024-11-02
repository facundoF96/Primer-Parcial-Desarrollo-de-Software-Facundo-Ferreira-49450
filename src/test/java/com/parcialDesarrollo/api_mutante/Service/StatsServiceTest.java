package com.parcialDesarrollo.api_mutante.Service;

import com.parcialDesarrollo.api_mutante.Repository.MutantRepository;
import com.parcialDesarrollo.api_mutante.dto.StatsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class StatsServiceTest {

    @Mock
    private MutantRepository mutantRepository;

    @InjectMocks
    private StatsService statsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStats_WithNoHumans_ReturnsZeroRatio() {
        // Arrange
        when(mutantRepository.countByIsMutantTrue()).thenReturn(5L);
        when(mutantRepository.countByIsMutantFalse()).thenReturn(0L);

        // Act
        StatsResponse statsResponse = statsService.getStats();

        // Assert
        assertEquals(5L, statsResponse.getCountMutantDna());
        assertEquals(0L, statsResponse.getCountHumanDna());
        assertEquals(0.0, statsResponse.getRatio());
    }

    @Test
    void getStats_WithHumansAndMutants_ReturnsCorrectStats() {
        // Arrange
        when(mutantRepository.countByIsMutantTrue()).thenReturn(10L);
        when(mutantRepository.countByIsMutantFalse()).thenReturn(5L);

        // Act
        StatsResponse statsResponse = statsService.getStats();

        // Assert
        assertEquals(10L, statsResponse.getCountMutantDna());
        assertEquals(5L, statsResponse.getCountHumanDna());
        assertEquals(2.0, statsResponse.getRatio());
    }

    @Test
    void getStats_WithOnlyHumans_ReturnsZeroMutantsAndCorrectRatio() {
        // Arrange
        when(mutantRepository.countByIsMutantTrue()).thenReturn(0L);
        when(mutantRepository.countByIsMutantFalse()).thenReturn(3L);

        // Act
        StatsResponse statsResponse = statsService.getStats();

        // Assert
        assertEquals(0L, statsResponse.getCountMutantDna());
        assertEquals(3L, statsResponse.getCountHumanDna());
        assertEquals(0.0, statsResponse.getRatio());
    }

    @Test
    void getStats_WithNoDna_ReturnsZeroCountsAndRatio() {
        // Arrange
        when(mutantRepository.countByIsMutantTrue()).thenReturn(0L);
        when(mutantRepository.countByIsMutantFalse()).thenReturn(0L);

        // Act
        StatsResponse statsResponse = statsService.getStats();

        // Assert
        assertEquals(0L, statsResponse.getCountMutantDna());
        assertEquals(0L, statsResponse.getCountHumanDna());
        assertEquals(0.0, statsResponse.getRatio());
    }
}
