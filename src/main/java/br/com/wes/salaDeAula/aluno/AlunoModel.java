package br.com.wes.salaDeAula.aluno;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "alunos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int idade;

    @Column(name = "nota_do_primeiro_semestre", nullable = false)
    private float notaDoPrimeiroSemestre;

    @Column(name = "nota_do_segundo_semestre", nullable = false)
    private float notaDoSegundoSemestre;

    @Column(name = "nome_do_professor", nullable = false)
    private String nomeDoProfessor;

    @Column(name = "numero_da_sala" , nullable = false)
    private String numeroDaSala;


    public AlunoModel(AlunoRequestPayload payload) {
        this.nome = payload.nome();
        this.idade = payload.idade();
        this.notaDoPrimeiroSemestre = payload.nota_do_primeiro_semestre();
        this.notaDoSegundoSemestre = payload.nota_do_segundo_semestre();
        this.nomeDoProfessor = payload.nome_do_professor();
        this.numeroDaSala = payload.numero_da_sala();
    }
}

