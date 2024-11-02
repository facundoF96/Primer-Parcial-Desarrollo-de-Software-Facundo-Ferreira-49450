package com.parcialDesarrollo.api_mutante.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DnaResult {

    private List<String> dnaSequences;

    private boolean isMutant;
}
