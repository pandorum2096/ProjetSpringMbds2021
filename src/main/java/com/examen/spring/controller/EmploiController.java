package com.examen.spring.controller;

import com.examen.spring.exception.ResourceNotFoundException;
import com.examen.spring.model.Classe;
import com.examen.spring.model.Emploi;
import com.examen.spring.model.Enseignant;
import com.examen.spring.model.Matiere;
import com.examen.spring.repository.ClasseRepository;
import com.examen.spring.repository.EmploiRepository;
import com.examen.spring.repository.EnseignantRepository;
import com.examen.spring.repository.MatiereRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import com.examen.*;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by dymama
 */
@RestController
@RequestMapping("/api")
public class EmploiController {

    @Autowired
    EmploiRepository emploiRepository;
    
   
    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    MatiereRepository matiereRepository;
    
    @Autowired
    ClasseRepository classeRepository;


    @GetMapping("/emplois")
    public List<Emploi> getAllEmmplois() {
        return emploiRepository.findAll();
    }

    @PostMapping("/emplois/{enseignantId}/{matiereId}/{classeId}")
    public Emploi createEmploi(@PathVariable(value = "enseignantId") Long enseignantId,@PathVariable (value = "matiereId") Long matiereId,@PathVariable (value = "classeId") Long classeId,@Valid @RequestBody Emploi emploi,Pageable pageable) {
    	
    	Enseignant enseignant = enseignantRepository.findById(enseignantId).orElseThrow(() -> new ResourceNotFoundException("Emploi", "id", enseignantId));
    	Matiere matiere = matiereRepository.findById( matiereId).orElseThrow(() -> new ResourceNotFoundException("Matiere", "id",  matiereId));
    	Classe classe = classeRepository.findById(classeId).orElseThrow(() -> new ResourceNotFoundException("Classe", "id",classeId));
    	emploi.setEnseignant(enseignant);
    	emploi.setMatiere(matiere);
    	emploi.setClasse(classe);
        return emploiRepository.save(emploi);
       
    }

    @GetMapping("/emplois/{id}")
    public Emploi getEmploiById(@PathVariable(value = "id") Long emploiId) {
        return emploiRepository.findById(emploiId)
                .orElseThrow(() -> new ResourceNotFoundException("Emploi:", "id", emploiId));
    }

    @PutMapping("/emplois/{id}/{enseignantId}/{matiereId}/{classeId}")
    public Emploi updateEmploi(@PathVariable(value = "id") Long emploiId,@PathVariable(value = "enseignantId") Long enseignantId,@PathVariable (value = "matiereId") Long matiereId,@PathVariable (value = "classeId") Long classeId,
                                           @Valid @RequestBody Emploi emploiDetails,Pageable pageable) {

        Emploi emploi = emploiRepository.findById(emploiId)
                .orElseThrow(() -> new ResourceNotFoundException("Emploi", "id", emploiId));

        Enseignant enseignant = enseignantRepository.findById(enseignantId).orElseThrow(() -> new ResourceNotFoundException("Emploi", "id", enseignantId));
    	Matiere matiere = matiereRepository.findById( matiereId).orElseThrow(() -> new ResourceNotFoundException("Matiere", "id",  matiereId));
    	Classe classe = classeRepository.findById(classeId).orElseThrow(() -> new ResourceNotFoundException("Classe", "id",classeId));
        emploi.setLibelle(emploiDetails.getLibelle());
        emploi.setDateDebut(emploiDetails.getDateDebut());
        emploi.setHeureDebut(emploiDetails.getHeureDebut());
        emploi.setDateFin(emploiDetails.getDateFin());
        emploi.setHeureFin(emploiDetails.getHeureFin());
        emploi.setEnseignant(enseignant);
    	emploi.setMatiere(matiere);
    	emploi.setClasse(classe);
        
        Emploi updatedEmploi = emploiRepository.save(emploi);
        return updatedEmploi;
    }

    @DeleteMapping("/emplois/{id}")
    public ResponseEntity<?> deleteEmploi(@PathVariable(value = "id") Long emploiId) {
        Emploi emploi = emploiRepository.findById(emploiId)
                .orElseThrow(() -> new ResourceNotFoundException("Emploi", "id", emploiId));

        emploiRepository.delete(emploi);

        return ResponseEntity.ok().build();
    }
}
