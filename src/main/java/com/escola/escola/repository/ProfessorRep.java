package com.escola.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escola.escola.model.ProfessorModel;

public interface ProfessorRep extends JpaRepository<ProfessorModel, Long> {
   
}
