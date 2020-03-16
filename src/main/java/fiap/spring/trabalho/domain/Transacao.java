package fiap.spring.trabalho.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Transacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer idTransacao;	
	private long valorCompra;
	private Date dataCompra;
	@ManyToOne
	@JoinColumn(name="aluno_rm")
	private Aluno aluno;
	
	public Transacao(){
		
	}

	public Transacao(Integer id, long valorCompra, Date dataCompra) {
		super();
		idTransacao = id;
		this.valorCompra = valorCompra;
		this.dataCompra = dataCompra;
	}

	public long getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(long valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Integer getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(Integer idTransacao) {
		this.idTransacao = idTransacao;
	}
	
	
}
