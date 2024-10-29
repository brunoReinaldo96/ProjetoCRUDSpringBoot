package com.escola.escola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escola.escola.model.AlunoModel;
import com.escola.escola.service.AlunoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Operation(summary = "Listar Todos os Alunos", description = "Retorna uma lista de todos os alunos registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtida com sucesso",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = AlunoModel.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping(produces = "application/json")
    public List<AlunoModel> listarTodos() {
        return alunoService.listarTodos();
    }

    @Operation(summary = "Buscar Aluno por ID", description = "Retorna um aluno baseado no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aluno encontrado",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = AlunoModel.class))),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content)
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<AlunoModel> buscarPorId(@PathVariable long id) {
        return alunoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salvar Aluno", description = "Salva um novo aluno")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Aluno salvo com sucesso",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = AlunoModel.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    public AlunoModel salvar(@RequestBody AlunoModel alunoModel) {
        return alunoService.salvar(alunoModel);
    }

    @Operation(summary = "Atualizar Aluno", description = "Atualiza um aluno existente baseado no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = AlunoModel.class))),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AlunoModel> atualizar(@PathVariable Long id, @RequestBody AlunoModel alunoModel) {
        return alunoService.atualizar(id, alunoModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar Aluno", description = "Deleta um aluno baseado no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Aluno deletado com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado", content = @Content)
    })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
