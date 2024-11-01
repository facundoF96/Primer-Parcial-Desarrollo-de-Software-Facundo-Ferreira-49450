package com.parcialDesarrollo.api_mutante.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DnaResult {
    // Eliminar el campo 'id' porque no es necesario en un DTO
    // private Long id;

    // Puedes mantener esta lista si deseas devolver las secuencias de ADN
    // Pero no será necesario en todos los casos, dependiendo de cómo uses el DTO
    private List<String> dnaSequences; // Cambiar a String o lo que necesites

    private boolean isMutant;
}
