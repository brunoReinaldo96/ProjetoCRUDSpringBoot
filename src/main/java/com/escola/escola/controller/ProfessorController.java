package com.escola.escola.controller;

import java.util.List;
import java.util.Optional;

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

import com.escola.escola.model.ProfessorModel;
import com.escola.escola.service.ProfessorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Operation(summary = "Listar Todos os Professores", description = "Retorna uma lista de todos os professores registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtida com sucesso",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ProfessorModel.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content)
    })
    @GetMapping(produces = "application/json")
    public List<ProfessorModel> listarTodos() {
        return professorService.listarTodos();
    }

    @Operation(summary = "Buscar Professor por ID", description = "Retorna um professor baseado no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Professor encontrado",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ProfessorModel.class))),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado", content = @Content)
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProfessorModel> buscarPorId(@PathVariable long id) {
        return professorService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salvar Professor", description = "Salva um novo professor")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Professor salvo com sucesso",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ProfessorModel.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ProfessorModel salvar(@RequestBody ProfessorModel professorModel) {
        return professorService.salvar(professorModel);
    }

    @Operation(summary = "Atualizar Professor", description = "Atualiza um professor baseado no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ProfessorModel.class))),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado", content = @Content)
    })
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProfessorModel> atualizar(@PathVariable Long id, @RequestBody ProfessorModel professorModel) {
        Optional<ProfessorModel> professorAtualizado = professorService.atualizar(id, professorModel);
        return professorAtualizado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar Professor", description = "Deleta um professor baseado no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Professor deletado com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado", content = @Content)
    })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        professorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
