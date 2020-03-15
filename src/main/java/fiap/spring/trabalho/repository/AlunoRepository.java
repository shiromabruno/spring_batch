package fiap.spring.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiap.spring.trabalho.domain.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

	
}
