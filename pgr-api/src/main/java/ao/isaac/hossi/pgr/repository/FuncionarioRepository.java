package ao.isaac.hossi.pgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.isaac.hossi.pgr.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
