package com.escola.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escola.escola.model.AlunoModel;
import com.escola.escola.repository.AlunoRep;

@Service
public class AlunoService {

    @Autowired
    private AlunoRep alunoRep;

    public List<AlunoModel> listarTodos() {
        return alunoRep.findAll();
    }

    public Optional<AlunoModel> buscarPorId(Long id) {
        return alunoRep.findById(id);
    }

    public AlunoModel salvar(AlunoModel alunoModel) {
        return alunoRep.save(alunoModel);
    }

    public void deletar(long id) {
        alunoRep.deleteById(id);
    }

    public Optional<AlunoModel> atualizar(Long id, AlunoModel alunoModel) {

        return alunoRep.findById(id).map(alunoExistente -> {

            alunoExistente.setNome(alunoModel.getNome());
            alunoExistente.setMatricula(alunoModel.getMatricula());

            return alunoRep.save(alunoExistente);
        });
    }

}
