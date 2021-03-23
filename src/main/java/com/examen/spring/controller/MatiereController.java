package com.examen.spring.controller;

import com.examen.spring.exception.ResourceNotFoundException;
import com.examen.spring.model.Classe;
import com.examen.spring.model.Emploi;
import com.examen.spring.model.Matiere;
import com.examen.spring.repository.ClasseRepository;
import com.examen.spring.repository.EmploiRepository;
import com.examen.spring.repository.MatiereRepository;

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
public class MatiereController {

    @Autowired
    MatiereRepository matiereRepository;

    @GetMapping("/matieres")
    public List<Matiere> getAllMatiere() {
        return matiereRepository.findAll();
    }

    @PostMapping("/matieres")
    public Matiere createClasse(@Valid @RequestBody Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    @GetMapping("/matieres/{id}")
    public Matiere getMatiereById(@PathVariable(value = "id") Long matiereId) {
        return matiereRepository.findById(matiereId)
                .orElseThrow(() -> new ResourceNotFoundException("Classe:", "id", matiereId));
    }

    @PutMapping("/matieres/{id}")
    public Matiere updateMatiere(@PathVariable(value = "id") Long matiereId,
                                           @Valid @RequestBody Matiere matiereDetails) {

    	Matiere matiere = matiereRepository.findById(matiereId)
                .orElseThrow(() -> new ResourceNotFoundException("Matiere:", "id", matiereId));

    	matiere.setLibelle(matiereDetails.getLibelle());
        Matiere updatedMatiere = matiereRepository.save(matiere);
        return updatedMatiere;
    }

    @DeleteMapping("/materes/{id}")
    public ResponseEntity<?> deleteClasse(@PathVariable(value = "id") Long matiereId) {
    	Matiere matiere = matiereRepository.findById(matiereId)
                .orElseThrow(() -> new ResourceNotFoundException("Matiere:", "id", matiereId));

    	matiereRepository.delete(matiere);
        return ResponseEntity.ok().build();
    }
}
