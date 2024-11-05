package com.parcialDesarrollo.api_mutante.Service;

import com.parcialDesarrollo.api_mutante.Entities.Dna;
import com.parcialDesarrollo.api_mutante.dto.DnaRequest;
import com.parcialDesarrollo.api_mutante.dto.DnaResult;
import com.parcialDesarrollo.api_mutante.dto.StatsResponse;
import com.parcialDesarrollo.api_mutante.Repository.DnaRepository;
import com.parcialDesarrollo.api_mutante.Repository.MutantRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MutantService {

    private final DnaRepository dnaRepository;
    private final MutantRepository mutantRepository;

    public MutantService(DnaRepository dnaRepository, MutantRepository mutantRepository) {
        this.dnaRepository = dnaRepository;
        this.mutantRepository = mutantRepository;
    }

    public boolean isMutant(DnaRequest dnaRequest) {
        if (dnaRequest == null || dnaRequest.getDna() == null || dnaRequest.getDna().length == 0) {
            throw new IllegalArgumentException("No puede ser nulo ni estar vacio");
        }

        for (String row : dnaRequest.getDna()) {
        if (!row.matches("[ACGT]+")) {
            throw new IllegalArgumentException("El ADN solo puede contener las letras A, C, G y T");
            }
        }

        String dnaString = String.join(",", dnaRequest.getDna());

        Dna existingDna = dnaRepository.findByDna(dnaString).orElse(null);
        if (existingDna != null) {
            return existingDna.isMutant();
        }


        boolean isMutant = verificarSecuencia(dnaRequest.getDna());


        Dna newDna = new Dna();
        newDna.setDna(dnaString);
        newDna.setMutant(isMutant);


        dnaRepository.save(newDna);


        DnaResult result = new DnaResult();
        result.setMutant(isMutant);
        result.setDnaSequences(Arrays.asList(dnaRequest.getDna()));


        return isMutant;
    }

    protected boolean verificarSecuencia(String[] dna) {
        int n = dna.length;
        int secuenciasEncontradas = 0;

        for (int index = 0; index < n * n; index++) {
            int i = index / n;
            int j = index % n;


            if (i >= n || j >= dna[i].length()) {
                continue;
            }

            char letra = dna[i].charAt(j);


            if (j + 3 < n &&
                    letra == dna[i].charAt(j + 1) &&
                    letra == dna[i].charAt(j + 2) &&
                    letra == dna[i].charAt(j + 3)) {
                secuenciasEncontradas++;
            }


            if (i + 3 < n &&
                    letra == dna[i + 1].charAt(j) &&
                    letra == dna[i + 2].charAt(j) &&
                    letra == dna[i + 3].charAt(j)) {
                secuenciasEncontradas++;
            }


            if (i + 3 < n && j + 3 < n &&
                    letra == dna[i + 1].charAt(j + 1) &&
                    letra == dna[i + 2].charAt(j + 2) &&
                    letra == dna[i + 3].charAt(j + 3)) {
                secuenciasEncontradas++;
            }


            if (i + 3 < n && j - 3 >= 0 &&
                    letra == dna[i + 1].charAt(j - 1) &&
                    letra == dna[i + 2].charAt(j - 2) &&
                    letra == dna[i + 3].charAt(j - 3)) {
                secuenciasEncontradas++;
            }


            if (secuenciasEncontradas > 1) {
                return true;
            }
        }

        return false;
    }


    public StatsResponse getStats() {
        long countMutantDna = mutantRepository.countByIsMutantTrue();
        long countHumanDna = mutantRepository.countByIsMutantFalse();
        double ratio = (countHumanDna == 0) ? 0 : (double) countMutantDna / countHumanDna;

        return new StatsResponse(countMutantDna, countHumanDna, ratio);
    }
}
