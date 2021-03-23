package com.examen.spring.controller;

import com.examen.spring.exception.ResourceNotFoundException;
import com.examen.spring.model.Emploi;
import com.examen.spring.model.Enseignant;
import com.examen.spring.repository.EmploiRepository;
import com.examen.spring.repository.EnseignantRepository;

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
public class EnseignantController {

    @Autowired
    EnseignantRepository enseignantRepository;

    @GetMapping("/enseignants")
    public List<Enseignant> getAllEnseignant() {
        return enseignantRepository.findAll();
    }

    @PostMapping("/enseignants")
    public Enseignant createEnseignant(@Valid @RequestBody Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    @GetMapping("/enseignants/{id}")
    public Enseignant getEnseignantById(@PathVariable(value = "id") Long enseignantId) {
        return enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new ResourceNotFoundException("Enseignant:", "id", enseignantId));
    }

    @PutMapping("/enseignants/{id}")
    public Enseignant updateEnseignant(@PathVariable(value = "id") Long enseignantId,
                                           @Valid @RequestBody Enseignant enseignantDetails) {

        Enseignant enseignant= enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new ResourceNotFoundException("Enseignant", "id", enseignantId));

        enseignant.setNom(enseignantDetails.getNom());
        enseignant.setContact(enseignantDetails.getContact());
        enseignant.setStatut(enseignantDetails.getStatut());
        enseignant.setEmail(enseignantDetails.getEmail());
        Enseignant updatedEnseignant = enseignantRepository.save(enseignant);
        return updatedEnseignant;
    }

    @DeleteMapping("/enseignants/{id}")
    public ResponseEntity<?> deleteEnseignant(@PathVariable(value = "id") Long enseignantId) {
        Enseignant enseignant = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new ResourceNotFoundException("Enseignant", "id", enseignantId));

        enseignantRepository.delete(enseignant);

        return ResponseEntity.ok().build();
    }
}
