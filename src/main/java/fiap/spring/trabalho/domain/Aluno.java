package fiap.spring.trabalho.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Aluno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer Rm;	
	private String nome;
	private String curso;
	
	@OneToMany(mappedBy="aluno")
	private List<Transacao> transacoes = new ArrayList<>();
	
	public Aluno(){
		
	}
	
	public Aluno(Integer rm, String nome, String curso) {
		super();
		Rm = rm;
		this.nome = nome;
		this.curso = curso;
	}
	public Integer getRm() {
		return Rm;
	}
	public void setRm(Integer rm) {
		Rm = rm;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}
	
	
}
