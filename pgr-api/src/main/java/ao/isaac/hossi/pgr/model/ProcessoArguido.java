package ao.isaac.hossi.pgr.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
//@Table(name="processo_arguido")
public class ProcessoArguido {

	@ManyToOne
	@JoinColumn(name="produto_id",nullable = false)
	private Processo processo;
	
	@ManyToOne
	@JoinColumn(name="pedido_id",nullable = false)
	private Arguido arguido;
}
