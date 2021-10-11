package br.com.boot.spring.biblioteca.camadas.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.biblioteca.camadas.entity.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {
	
	List<Local> getByCodigo(Integer codigo);
	
	List<Local> findByNomeContainingIgnoreCase(String nome, Sort sort);
	
	@Query(nativeQuery = true)
	List<Local> buscarNativeQuery(Integer codigo, String nome);

}
