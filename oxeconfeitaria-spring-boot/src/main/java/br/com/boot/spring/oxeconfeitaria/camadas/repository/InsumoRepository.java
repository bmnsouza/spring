package br.com.boot.spring.oxeconfeitaria.camadas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.oxeconfeitaria.camadas.entity.Insumo;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Integer> {
	
	Optional<List<Insumo>> getByIdInsumo(Integer idInsumo);
	
	Optional<List<Insumo>> findByDsInsumoContainingIgnoreCase(String dsInsumo, Sort sort);
	
	@Query(nativeQuery = true)
	Optional<List<Insumo>> buscarNativeQuery(Integer idInsumo, String dsInsumo);

}
