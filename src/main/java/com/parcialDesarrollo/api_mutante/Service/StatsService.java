package com.parcialDesarrollo.api_mutante.Service;


import com.parcialDesarrollo.api_mutante.Repository.MutantRepository;
import com.parcialDesarrollo.api_mutante.dto.StatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class StatsService {

    private final MutantRepository mutantRepository;

    @Autowired
    public StatsService(MutantRepository mutantRepository){this.mutantRepository = mutantRepository;}

    public StatsResponse getStats(){
        long countMutantDna = mutantRepository.countByIsMutantTrue();
        long countHumanDna = mutantRepository.countByIsMutantFalse();
        double ratio = countHumanDna == 0 ? 0 : (double) countMutantDna / countHumanDna;
        return new StatsResponse(countMutantDna, countHumanDna, ratio);
    }

}
