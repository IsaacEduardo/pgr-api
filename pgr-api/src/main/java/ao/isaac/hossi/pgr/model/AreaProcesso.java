package ao.isaac.hossi.pgr.model;

public enum AreaProcesso {

	AREA_CRIMINAL("Área Criminal"), AREA_CIVIL_ADMINISTRATIVA("Área Civil e Administrativa"),
	AREA_LABORAL("Área Laboral"), AREA_MENORES("Área de Menores"), AREA_FAMILIA("Área de Família"),
	ACTIVIDADE_COMPLEMENTAR("Actividade Complementar ao Processo ou Extra-processual");

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private AreaProcesso(String nome) {
		this.nome = nome;
	}
	
	

}
