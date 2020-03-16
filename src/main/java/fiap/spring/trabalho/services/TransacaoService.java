package fiap.spring.trabalho.services;

import java.util.Optional;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fiap.spring.trabalho.domain.Transacao;
import fiap.spring.trabalho.exception.DataIntegrityException;
import fiap.spring.trabalho.exception.ObjectNotFoundException;
import fiap.spring.trabalho.repository.TransacaoRepository;


@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository repo;

	public Transacao find(Integer Rm) {
		Optional<Transacao> obj = repo.findById(Rm);  
	    
		  return obj.orElseThrow(() -> new ObjectNotFoundException(    
				"Transacao não encontrado! Rm: " + Rm)); 
		}
		
		public Transacao update(Transacao obj) {
			
			find(obj.getIdTransacao());
			return repo.save(obj);
		}
		
		public void delete(Integer Rm) {
			
			find(Rm);
			
			try {
			repo.deleteById(Rm);
			}
			catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Nao eh possivel Excluir Transacao");
				
			}
		}
		

}
