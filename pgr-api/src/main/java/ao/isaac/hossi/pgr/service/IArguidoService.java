package ao.isaac.hossi.pgr.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ao.isaac.hossi.pgr.model.Arguido;
import ao.isaac.hossi.pgr.model.Crime;
import ao.isaac.hossi.pgr.model.SituacaoArguido;

public interface IArguidoService {
	// listar todos
		public List<Arguido> findAll();

		// paginacao
		public Page<Arguido> findAll(Pageable pageable);

		public Arguido save(Arguido arguido);

		public void delete(Long id);

		public Arguido findById(Long id);

		public List<Crime> findAllCrimes();
		public List<SituacaoArguido> findAllSituacoes();
}
