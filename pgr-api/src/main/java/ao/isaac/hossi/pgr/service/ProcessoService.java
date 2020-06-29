package ao.isaac.hossi.pgr.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ao.isaac.hossi.pgr.model.Processo;

public interface ProcessoService {

	Set<Processo> getAll();

	// Processo findById(Long id);
	Optional<Processo> findById(Long id);

	Processo save(Processo processo);

	Page<Processo> findAll(PageRequest pageable);

	// Book findOne(Long id);

	Processo get(Long id);

	void delete(Long id);

	void removeOne(Long id);

	Page<Processo> listAll(int pageNumber, String sortField, String sortDir, String keyword);

}
