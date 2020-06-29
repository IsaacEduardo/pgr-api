package ao.isaac.hossi.pgr.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class ItemProcesso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "arguido_id", nullable = false)
	private Arguido arguido;
	@ManyToOne
	@JoinColumn(name = "processo_id", nullable = false)
	private Processo processo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Arguido getArguido() {
		return arguido;
	}

	public void setArguido(Arguido arguido) {
		this.arguido = arguido;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arguido == null) ? 0 : arguido.hashCode());
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
		ItemProcesso other = (ItemProcesso) obj;
		if (arguido == null) {
			if (other.arguido != null)
				return false;
		} else if (!arguido.equals(other.arguido))
			return false;
		return true;
	}

	@Transient
	public boolean isArguidoAssociado() {
		return this.getArguido() != null && this.getArguido().getId() != null;
	}

}
