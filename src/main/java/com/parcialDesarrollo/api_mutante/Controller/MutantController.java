package com.parcialDesarrollo.api_mutante.Controller;


import com.parcialDesarrollo.api_mutante.Service.MutantService;
import com.parcialDesarrollo.api_mutante.dto.DnaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("mutant")

public class MutantController {
    private final MutantService mutantService;

    public MutantController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping
    public ResponseEntity<String> isMutant(@RequestBody DnaRequest dnaRequest) {
        boolean isMutant = mutantService.isMutant(dnaRequest); // Aquí pasas el objeto DnaRequest completo

        if (isMutant) {
            return new ResponseEntity<>("Es mutante", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No es un mutante", HttpStatus.FORBIDDEN);
        }
    }

}
