package ao.isaac.hossi.pgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.isaac.hossi.pgr.model.Funcionario;
import ao.isaac.hossi.pgr.repository.FuncionarioRepository;
import ao.isaac.hossi.pgr.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public Funcionario save(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	public List<Funcionario> findAll() {
		return (List<Funcionario>) funcionarioRepository.findAll();
	}

	public Funcionario findOne(Long id) {
		return funcionarioRepository.getOne(id);
	}

	public void removeOne(Long id) {
		funcionarioRepository.deleteById(id);
	}
}
