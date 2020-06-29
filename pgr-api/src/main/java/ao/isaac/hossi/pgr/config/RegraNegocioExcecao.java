package ao.isaac.hossi.pgr.config;

public class RegraNegocioExcecao extends RuntimeException {

	private static final long serialVersionUID = 201807031413l;

	public RegraNegocioExcecao(String mensagem) {
		super(mensagem);
	}
}
