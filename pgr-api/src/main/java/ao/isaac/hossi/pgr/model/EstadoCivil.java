package ao.isaac.hossi.pgr.model;

public enum EstadoCivil {

	CASADO("Casado"), SOLTEIRO("SOLTEIRO"), DIVORCIADO("Divorciado"), VIUVO("Viúvo");

	private String nome;

	private EstadoCivil(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
