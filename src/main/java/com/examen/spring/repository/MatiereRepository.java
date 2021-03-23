package com.examen.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.spring.model.Matiere;

/**
 * Created by dymama
 */

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {

}
