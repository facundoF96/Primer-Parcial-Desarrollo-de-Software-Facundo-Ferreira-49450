package com.parcialDesarrollo.api_mutante.Controller;

import com.parcialDesarrollo.api_mutante.Service.StatsService;
import com.parcialDesarrollo.api_mutante.dto.StatsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class StatsControllerTest {

    private StatsController statsController;
    private StatsService statsService;

    @BeforeEach
    void setUp() {
        statsService = Mockito.mock(StatsService.class);
        statsController = new StatsController(statsService);
    }

    @Test
    void testGetStats_ReturnsStatsResponse() {
        StatsResponse statsResponse = new StatsResponse();
        statsResponse.setCountMutantDna(10);
        statsResponse.setCountHumanDna(100);
        statsResponse.setRatio(0.1);

        when(statsService.getStats()).thenReturn(statsResponse);

        StatsResponse response = statsController.getStats();

        assertNotNull(response);
        assertEquals(10, response.getCountMutantDna());
        assertEquals(100, response.getCountHumanDna());
        assertEquals(0.1, response.getRatio());
    }
}
