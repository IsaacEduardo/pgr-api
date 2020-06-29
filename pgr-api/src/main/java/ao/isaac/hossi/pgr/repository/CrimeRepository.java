package ao.isaac.hossi.pgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.isaac.hossi.pgr.model.Crime;

public interface CrimeRepository extends JpaRepository<Crime, Long>{

}
