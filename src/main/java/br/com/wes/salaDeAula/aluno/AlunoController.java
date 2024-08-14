package br.com.wes.salaDeAula.aluno;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;


    @Operation(description = "Busca todos os alunos registrados na base de dados")
    @ApiResponse(responseCode = "200", description = "Retorna todos os alunos registrados na base")

    @GetMapping("/listar")
    public List<AlunoModel> findAll(){

        return repository.findAll();

    }
    @Operation(description = "Busca o aluno correspondente ao id na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o registro do aluno na base "),
            @ApiResponse(responseCode = "404", description = "Nenhum registro do aluno informado na base")
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<AlunoModel> getAluno(@PathVariable UUID id){
        Optional<AlunoModel> aluno = this.repository.findById(id);

        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(description = "Registra um novo aluno na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok, aluno registrado"),
            @ApiResponse(responseCode = "500", description = "Aluno não registrado, campos não preenchidos corretamente")
    })
    @PostMapping("/criar")
    public ResponseEntity<AlunoCreateResponse> createAluno(@RequestBody AlunoRequestPayload payload){
        AlunoModel newAluno = new AlunoModel(payload);

        this.repository.save(newAluno);

        return ResponseEntity.ok(new AlunoCreateResponse(newAluno.getId()));
    }
    @Operation(description = "Altera o registro do aluno na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok, alterações registradas"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<AlunoModel> editaAluno(@PathVariable UUID id, @RequestBody AlunoRequestPayload payload){
        Optional<AlunoModel> aluno = this.repository.findById(id);

        if(aluno.isPresent()){
            AlunoModel editadoAluno = aluno.get();

            editadoAluno.setNome(payload.nome());
            editadoAluno.setIdade(payload.idade());
            editadoAluno.setNotaDoPrimeiroSemestre(payload.nota_do_primeiro_semestre());
            editadoAluno.setNotaDoSegundoSemestre(payload.nota_do_segundo_semestre());
            editadoAluno.setNomeDoProfessor(payload.nome_do_professor());
            editadoAluno.setNumeroDaSala(payload.numero_da_sala());

            this.repository.save(editadoAluno);
            return ResponseEntity.ok(editadoAluno);
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(description = "Deleta o registro do aluno na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok, Registro deletado"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<AlunoModel> delete(@PathVariable UUID id){
        Optional<AlunoModel> aluno = this.repository.findById(id);

        if (aluno.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
