package br.com.wes.salaDeAula.aluno;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlunoRepository extends JpaRepository<AlunoModel, UUID> {
}
