package com.escola.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escola.escola.model.ProfessorModel;
import com.escola.escola.repository.ProfessorRep;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRep professorRep;

    public List<ProfessorModel> listarTodos() {
        return professorRep.findAll();
    }

    public Optional<ProfessorModel> buscarPorId(Long id) {
        return professorRep.findById(id);
    }

    public ProfessorModel salvar(ProfessorModel professorModel) {
        return professorRep.save(professorModel);
    }

    public Optional<ProfessorModel> atualizar(Long id, ProfessorModel professorModel) {
        return professorRep.findById(id).map(professorExistente -> {
            professorExistente.setNome(professorModel.getNome());
            professorExistente.setMatricula(professorModel.getMatricula());

            return professorRep.save(professorExistente);
        });
    }

    public void deletar(long id) {
        professorRep.deleteById(id);
    }
}
