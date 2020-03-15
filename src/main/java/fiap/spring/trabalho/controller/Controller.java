package fiap.spring.trabalho.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



import fiap.spring.trabalho.domain.Aluno;
import fiap.spring.trabalho.services.AlunoService;

@RestController
@RequestMapping(value = "/alunos")
public class Controller {
	// FIAP
	@Autowired
	private AlunoService service;

	@RequestMapping(method=RequestMethod.POST)	
	public ResponseEntity<Void>	cadastraAluno(@RequestBody Aluno obj){
		   
			obj = service.insert(obj);
					URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{rm}").buildAndExpand(obj.getRm()).toUri();
			return ResponseEntity.created(uri).build();
		}
	
	@RequestMapping(value="/{rm}", method=RequestMethod.GET)
	public ResponseEntity<Aluno> find(@PathVariable Integer rm) {
	
		Aluno obj = service.find(rm);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{rm}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Aluno obj, @PathVariable Integer rm){
		obj.setRm(rm);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{rm}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer rm){
		service.delete(rm);
		return ResponseEntity.noContent().build();
	}
	
	
}
