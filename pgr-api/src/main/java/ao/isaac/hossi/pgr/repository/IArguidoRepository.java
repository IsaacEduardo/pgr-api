package ao.isaac.hossi.pgr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ao.isaac.hossi.pgr.model.Arguido;
import ao.isaac.hossi.pgr.model.Crime;
import ao.isaac.hossi.pgr.model.SituacaoArguido;

public interface IArguidoRepository extends JpaRepository<Arguido, Long> {

	@Query("from Crime")
	public List<Crime> findAllCrimes();

	@Query("from SituacaoArguido")
	public List<SituacaoArguido> findAllCituacoces();
}
