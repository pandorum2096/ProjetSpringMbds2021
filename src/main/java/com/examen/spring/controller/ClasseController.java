package com.examen.spring.controller;

import com.examen.spring.exception.ResourceNotFoundException;
import com.examen.spring.model.Classe;
import com.examen.spring.model.Emploi;
import com.examen.spring.repository.ClasseRepository;
import com.examen.spring.repository.EmploiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by dymama
 */
@RestController
@RequestMapping("/api")
public class ClasseController {

    @Autowired
    ClasseRepository classeRepository;

    @GetMapping("/classes")
    public List<Classe> getAllClasse() {
        return classeRepository.findAll();
    }

    @PostMapping("/classes")
    public Classe createClasse(@Valid @RequestBody Classe classe) {
        return classeRepository.save(classe);
    }

    @GetMapping("/classes/{id}")
    public Classe getClasseById(@PathVariable(value = "id") Long classeId) {
        return classeRepository.findById(classeId)
                .orElseThrow(() -> new ResourceNotFoundException("Classe:", "id", classeId));
    }

    @PutMapping("/classes/{id}")
    public Classe updateClasse(@PathVariable(value = "id") Long classeId,
                                           @Valid @RequestBody Classe classeDetails) {

        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new ResourceNotFoundException("Classe:", "id", classeId));

        classe.setLibelle(classeDetails.getLibelle());
        Classe updatedClasse = classeRepository.save(classe);
        return updatedClasse;
    }

    @DeleteMapping("/classes/{id}")
    public ResponseEntity<?> deleteClasse(@PathVariable(value = "id") Long classeId) {
    	Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new ResourceNotFoundException("Classe:", "id", classeId));

    	classeRepository.delete(classe);

        return ResponseEntity.ok().build();
    }
}
