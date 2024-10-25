package com.parcialDesarrollo.api_mutante.Repository;


import com.parcialDesarrollo.api_mutante.DnaResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MutantRepository extends JpaRepository<DnaResult, Long>{
}
