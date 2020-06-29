package ao.isaac.hossi.pgr.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Funcionario extends Pessoa {
	private static final long serialVersionUID = 1L;

	private String email;
	@ManyToOne
	private Funcao funcao;
	@ManyToOne
	private Categoria categoria;
	private String localTrabalho;
	private String tempoInicioServicoInstituicao;
	private String tempoTrabalhhoLocalFunciona;

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	public String getTempoInicioServicoInstituicao() {
		return tempoInicioServicoInstituicao;
	}

	public void setTempoInicioServicoInstituicao(String tempoInicioServicoInstituicao) {
		this.tempoInicioServicoInstituicao = tempoInicioServicoInstituicao;
	}

	public String getTempoTrabalhhoLocalFunciona() {
		return tempoTrabalhhoLocalFunciona;
	}

	public void setTempoTrabalhhoLocalFunciona(String tempoTrabalhhoLocalFunciona) {
		this.tempoTrabalhhoLocalFunciona = tempoTrabalhhoLocalFunciona;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
