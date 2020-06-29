package ao.isaac.hossi.pgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.isaac.hossi.pgr.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	

}
