package com.parcialDesarrollo.api_mutante.Repository;

import com.parcialDesarrollo.api_mutante.Entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DnaRepository extends JpaRepository<Dna, Long> {

    Optional<Dna> findByDna(String dna);

}
