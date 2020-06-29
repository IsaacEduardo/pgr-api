package ao.isaac.hossi.pgr.service;

import java.util.List;

import ao.isaac.hossi.pgr.model.Funcionario;

public interface FuncionarioService {
	Funcionario save(Funcionario funcionario);

	List<Funcionario> findAll();
	
	Funcionario findOne(Long id);
	
	void removeOne(Long id);

	
	
}
