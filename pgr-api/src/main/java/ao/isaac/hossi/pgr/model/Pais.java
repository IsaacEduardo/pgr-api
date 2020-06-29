package ao.isaac.hossi.pgr.model;

public enum Pais {

	AO("Angola"), PT("Portugal"), BR("Brasil"), RDC("Republica Democrática do Congo");

	private String nome;

	private Pais(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
