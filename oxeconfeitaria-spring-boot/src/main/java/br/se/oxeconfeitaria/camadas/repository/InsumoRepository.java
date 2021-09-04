package br.se.oxeconfeitaria.camadas.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.se.oxeconfeitaria.camadas.entity.Insumo;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Integer> {
	
	List<Insumo> getByIdInsumo(Integer idInsumo);
	
	List<Insumo> findByDsInsumoContainingIgnoreCase(String dsInsumo, Sort sort);
	
}