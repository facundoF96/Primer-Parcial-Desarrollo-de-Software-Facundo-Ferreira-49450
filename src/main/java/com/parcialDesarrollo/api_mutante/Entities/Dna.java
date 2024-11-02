package com.parcialDesarrollo.api_mutante.Entities;


import com.parcialDesarrollo.api_mutante.dto.DnaResult;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Table(name = "dna_sequences")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Dna extends Base implements Serializable {

    @Column(nullable = false)
    private String dna;

    @Column(name = "is_mutant", nullable = false)
    private boolean isMutant;

}

