package br.com.wes.salaDeAula.aluno;

public record AlunoRequestPayload(String nome, int idade, float nota_do_primeiro_semestre, float nota_do_segundo_semestre, String nome_do_professor, String numero_da_sala) {
}
