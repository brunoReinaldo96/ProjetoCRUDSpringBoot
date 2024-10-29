package com.escola.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escola.escola.model.AlunoModel;

public interface AlunoRep extends JpaRepository<AlunoModel, Long> {

}
