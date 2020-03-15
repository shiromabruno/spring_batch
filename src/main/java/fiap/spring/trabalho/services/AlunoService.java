package fiap.spring.trabalho.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import fiap.spring.trabalho.domain.Aluno;
import fiap.spring.trabalho.exception.DataIntegrityException;
import fiap.spring.trabalho.exception.ObjectNotFoundException;
import fiap.spring.trabalho.repository.AlunoRepository;


@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository repo;

	public Aluno find(Integer Rm) {
		Optional<Aluno> obj = repo.findById(Rm);  
	    
		  return obj.orElseThrow(() -> new ObjectNotFoundException(    
				"Aluno não encontrado! Rm: " + Rm)); 
		}
		
		public Aluno insert(Aluno obj) {
			
			obj.setRm(null);
			
			return repo.save(obj);
		}
		
		public Aluno update(Aluno obj) {
			
			find(obj.getRm());
			return repo.save(obj);
		}
		
		public void delete(Integer Rm) {
			
			find(Rm);
			
			try {
			repo.deleteById(Rm);
			}
			catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Nao eh possivel Excluir Aluno");
				
			}
		}
}
