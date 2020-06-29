package ao.isaac.hossi.pgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ao.isaac.hossi.pgr.model.Arguido;
import ao.isaac.hossi.pgr.model.Crime;
import ao.isaac.hossi.pgr.model.SituacaoArguido;
import ao.isaac.hossi.pgr.repository.IArguidoRepository;
import ao.isaac.hossi.pgr.service.IArguidoService;

@Service
public class ArguidoServceImpl implements IArguidoService {

	@Autowired
	private IArguidoRepository arguidoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Arguido> findAll() {
		return (List<Arguido>) arguidoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Arguido> findAll(Pageable pageable) {

		return arguidoRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public Arguido save(Arguido arguido) {
		return arguidoRepository.save(arguido);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		arguidoRepository.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Arguido findById(Long id) {

		return arguidoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Crime> findAllCrimes() {
		return arguidoRepository.findAllCrimes();
	}

	@Override
	@Transactional(readOnly = true)
	public List<SituacaoArguido> findAllSituacoes() {
		return arguidoRepository.findAllCituacoces();
	}

}
