package com.parcialDesarrollo.api_mutante.Controller;


import com.parcialDesarrollo.api_mutante.Service.StatsService;
import com.parcialDesarrollo.api_mutante.dto.StatsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")

public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService){ this.statsService = statsService;}

    @GetMapping
    public StatsResponse getStats() {
        return statsService.getStats();
    }

}
