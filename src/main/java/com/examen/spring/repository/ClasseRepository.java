package com.examen.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.spring.model.Classe;

/**
 * Created by dymama
 */

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

}
