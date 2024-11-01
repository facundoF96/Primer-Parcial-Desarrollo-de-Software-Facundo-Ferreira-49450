package com.parcialDesarrollo.api_mutante.Repository;


import com.parcialDesarrollo.api_mutante.Entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MutantRepository extends JpaRepository<Dna, Long>{

    Optional<Dna> findByDna(String dna);

    long countByIsMutant(boolean isMutant);

    long countByIsMutantTrue();  // Contar ADN mutante
    long countByIsMutantFalse();
}
