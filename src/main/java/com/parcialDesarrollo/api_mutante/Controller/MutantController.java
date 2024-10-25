package com.parcialDesarrollo.api_mutante.Controller;


import com.parcialDesarrollo.api_mutante.Service.MutantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor

public class MutantController {
    private final MutantService mutantService;

    @PostMapping("/")
    public ResponseEntity<String> isMutant(@RequestBody String[] dna) {
        boolean isMutant = mutantService.isMutant(dna);

        if (isMutant) {
            return new ResponseEntity<>("Es mutante", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No es un mutante", HttpStatus.FORBIDDEN);
        }
    }
}
