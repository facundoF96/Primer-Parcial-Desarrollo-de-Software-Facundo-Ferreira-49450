package com.parcialDesarrollo.api_mutante.Service;


import com.parcialDesarrollo.api_mutante.DnaResult;
import com.parcialDesarrollo.api_mutante.Repository.MutantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class MutantService {

    private final MutantRepository mutantRepository;

    public boolean isMutant(String[] dna){
        boolean isMutant = verificarSecuencia(dna);

        DnaResult result = new DnaResult(null, dna, isMutant);
        mutantRepository.save(result);

        return isMutant;
    }

    private boolean verificarSecuencia(String[] dna){
        int n = dna.length;
        int secuenciasEncontradas = 0;

        for (int index = 0; index < n * n; index++) {
            int i = index / n;
            int j = index % n;
            char letra = dna[i].charAt(j);

            if (j + 3 < n &&
                    letra == dna[i].charAt(j + 1) &&
                    letra == dna[i].charAt(j + 2) &&
                    letra == dna[i].charAt(j + 3)) secuenciasEncontradas++;

            if (i + 3 < n &&
                    letra == dna[i + 1].charAt(j) &&
                    letra == dna[i + 2].charAt(j) &&
                    letra == dna[i + 3].charAt(j)) secuenciasEncontradas++;

            if (i + 3 < n && j + 3 < n &&
                    letra == dna[i + 1].charAt(j + 1) &&
                    letra == dna[i + 2].charAt(j + 2) &&
                    letra == dna[i + 3].charAt(j + 3)) secuenciasEncontradas++;

            if (i + 3 < n && j - 3 >= 0 &&
                    letra == dna[i + 1].charAt(j - 1) &&
                    letra == dna[i + 2].charAt(j - 2) &&
                    letra == dna[i + 3].charAt(j - 3)) secuenciasEncontradas++;

            if (secuenciasEncontradas > 1) return true;
        }

        return false;
    }
}
