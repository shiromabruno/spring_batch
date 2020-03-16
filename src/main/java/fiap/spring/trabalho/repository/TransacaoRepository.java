package fiap.spring.trabalho.repository;

import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import fiap.spring.trabalho.domain.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

	
	
}
