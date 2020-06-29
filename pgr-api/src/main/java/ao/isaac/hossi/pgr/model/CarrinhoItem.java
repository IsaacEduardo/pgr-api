package ao.isaac.hossi.pgr.model;

import java.io.Serializable;

public class CarrinhoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Arguido arguido;

	public CarrinhoItem(Arguido arguido) {
		this.arguido = arguido;

	}

	public Arguido getArguido() {
		return arguido;
	}

	public void setArguido(Arguido arguido) {
		this.arguido = arguido;
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
		CarrinhoItem other = (CarrinhoItem) obj;
		if (arguido == null) {
			if (other.arguido != null)
				return false;
		} else if (!arguido.equals(other.arguido))
			return false;
		return true;
	}

}
