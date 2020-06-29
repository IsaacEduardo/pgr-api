package ao.isaac.hossi.pgr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "arguidos")
public class Arguido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotEmpty(message = "O campo nome não pode estar vazio")
	@Size(min = 4, max = 25, message = "O nome deve possuir pelomenos mas de 4 carateres")
	private String nome;

	@NotEmpty(message = "O campo não pode estar vazio")
	private String filhoDe;

	@NotEmpty(message = "O campo não pode estar vazio")
	private String efilhoDe;

	@NotEmpty(message = "O campo não pode estar vazio")
	@Column(nullable = false, unique = true)
	private String bi;

	@DateTimeFormat(pattern = "yyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@NotEmpty(message = "O campo não pode estar vazio")
	private String naturalidade;

	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;

	@Enumerated(EnumType.STRING)
	private Pais pais;

	@NotNull(message = "O campo crime não pode ser nulo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "crime_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Crime crime;

	@NotNull(message = "O campo situacao não pode ser nulo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "situacao_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private SituacaoArguido situacao;

	@DateTimeFormat(pattern = "yyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataDetencao;

	@DateTimeFormat(pattern = "yyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataLegalizacao;

	@NotEmpty(message = "O campo não pode estar vazio")
	private String condicaoRecepcao;

	@NotEmpty(message = "O campo não pode estar vazio")
	private String qualidade;

	@NotEmpty(message = "O campo não pode estar vazio")
	private String contactoP;

	@NotEmpty(message = "O campo não pode estar vazio")
	private String contactoA;

	@NotEmpty(message = "O campo não pode estar vazio")
	@Email(message = "email informádo não é valido")
	@Column(nullable = false, unique = true)
	private String email;

	private String foto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFilhoDe() {
		return filhoDe;
	}

	public void setFilhoDe(String filhoDe) {
		this.filhoDe = filhoDe;
	}

	public String getEfilhoDe() {
		return efilhoDe;
	}

	public void setEfilhoDe(String efilhoDe) {
		this.efilhoDe = efilhoDe;
	}

	public String getBi() {
		return bi;
	}

	public void setBi(String bi) {
		this.bi = bi;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Crime getCrime() {
		return crime;
	}

	public void setCrime(Crime crime) {
		this.crime = crime;
	}

	public SituacaoArguido getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoArguido situacao) {
		this.situacao = situacao;
	}

	public Date getDataDetencao() {
		return dataDetencao;
	}

	public void setDataDetencao(Date dataDetencao) {
		this.dataDetencao = dataDetencao;
	}

	public Date getDataLegalizacao() {
		return dataLegalizacao;
	}

	public void setDataLegalizacao(Date dataLegalizacao) {
		this.dataLegalizacao = dataLegalizacao;
	}

	public String getCondicaoRecepcao() {
		return condicaoRecepcao;
	}

	public void setCondicaoRecepcao(String condicaoRecepcao) {
		this.condicaoRecepcao = condicaoRecepcao;
	}

	public String getQualidade() {
		return qualidade;
	}

	public void setQualidade(String qualidade) {
		this.qualidade = qualidade;
	}

	public String getContactoP() {
		return contactoP;
	}

	public void setContactoP(String contactoP) {
		this.contactoP = contactoP;
	}

	public String getContactoA() {
		return contactoA;
	}

	public void setContactoA(String contactoA) {
		this.contactoA = contactoA;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
