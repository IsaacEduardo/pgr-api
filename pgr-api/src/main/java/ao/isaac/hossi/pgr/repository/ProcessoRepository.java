package ao.isaac.hossi.pgr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import ao.isaac.hossi.pgr.model.Processo;

@Transactional
public interface ProcessoRepository extends PagingAndSortingRepository<Processo, Long> {

	// Pesquisa com multiplos filtros
	@Query("SELECT p FROM Processo p WHERE "
			+ "CONCAT(p.id, ' ', p.numeroProcesso, '', p.dataAbertura)"
			+ "LIKE %?1%")
	public Page<Processo> findAll(String keyword, Pageable pageable);

}
