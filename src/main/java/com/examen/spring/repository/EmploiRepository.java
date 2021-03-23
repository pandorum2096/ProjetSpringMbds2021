package com.examen.spring.repository;

import java.awt.print.Pageable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.spring.model.Emploi;

/**
 * Created by dymama
 */

@Repository
public interface EmploiRepository extends JpaRepository<Emploi, Long> {
	
	

}
