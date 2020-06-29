package ao.isaac.hossi.pgr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Processo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "Campo numero do processo é obrigatorio")
	@Column(nullable = false, unique = true)
	@Size(min = 6, max = 24, message = "O numero de processo deve possuir pelomenos mas de 6 carateres")
	private String numeroProcesso;

	
	@ManyToOne
	@JoinColumn(name = "instrutor_id", nullable = false)
	private Funcionario instrutor;
	
	@Enumerated(EnumType.STRING)
	private AreaProcesso areaProcesso;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "processo_arguido", joinColumns = { @JoinColumn(name = "processo_id") }, inverseJoinColumns = {
			@JoinColumn(name = "arguido_id") })
	private Set<Arguido> arguidos = new HashSet<>();

	@DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
	private Date dataAbertura;

	@Size(max = 100)
	@Column(name = "descricao", length = 100)
	private String descricao;

	@NotEmpty(message = "Campo instancia do processo é obrigatorio")
	private String instanciaAtual;

	@Lob
	private byte[] anexo;
	private String nomeFileAnexo;
	private String tipoFileAnexo;

	
	private String sku;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	public Funcionario getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Funcionario instrutor) {
		this.instrutor = instrutor;
	}

	public AreaProcesso getAreaProcesso() {
		return areaProcesso;
	}

	public void setAreaProcesso(AreaProcesso areaProcesso) {
		this.areaProcesso = areaProcesso;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getInstanciaAtual() {
		return instanciaAtual;
	}

	public void setInstanciaAtual(String instanciaAtual) {
		this.instanciaAtual = instanciaAtual;
	}

	public Set<Arguido> getArguidos() {
		return arguidos;
	}

	public void setArguidos(Set<Arguido> arguidos) {
		this.arguidos = arguidos;
	}

	public byte[] getAnexo() {
		return anexo;
	}

	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}

	public String getNomeFileAnexo() {
		return nomeFileAnexo;
	}

	public void setNomeFileAnexo(String nomeFileAnexo) {
		this.nomeFileAnexo = nomeFileAnexo;
	}



	public String getTipoFileAnexo() {
		return tipoFileAnexo;
	}

	public void setTipoFileAnexo(String tipoFileAnexo) {
		this.tipoFileAnexo = tipoFileAnexo;
	}
	
	
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		sku = sku.toUpperCase();
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Processo other = (Processo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public boolean isNovo() {
		return id == null;
	}

}
