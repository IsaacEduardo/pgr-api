package ao.isaac.hossi.pgr.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ao.isaac.hossi.pgr.model.Processo;
import ao.isaac.hossi.pgr.repository.ProcessoRepository;
import ao.isaac.hossi.pgr.service.ProcessoService;

@Service
public class ProcessoServiceImpl implements ProcessoService {

	@Autowired
	private ProcessoRepository processoRepository;

	@Override
	public Set<Processo> getAll() {

		Set<Processo> processos = new HashSet<>();
		processoRepository.findAll().iterator().forEachRemaining(processos::add);
		return processos;
	}

	public Optional<Processo> findById(Long id) {
		return processoRepository.findById(id);
	}

	@Transactional
	public Processo save(Processo processo) {

		processo = this.processoRepository.save(processo);

		return processo;
	}

	@Override
	public void delete(Long id) {
		processoRepository.deleteById(id);

	}

	@Override
	public void removeOne(Long id) {
		processoRepository.deleteById(id);

	}

	@Override
	public Page<Processo> findAll(PageRequest pageable) {
		Page<Processo> processos = processoRepository.findAll(pageable);

		return processos;
	}

	@Override
	public Processo get(Long id) {
		return processoRepository.findById(id).get();
	}

	@Override
	public Page<Processo> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

		Pageable pageable = PageRequest.of(pageNumber - 1, 10, sort); // 7 rows per page

		if (keyword != null) {
			return processoRepository.findAll(keyword, pageable);
		}
		return processoRepository.findAll(pageable);
	}

}
