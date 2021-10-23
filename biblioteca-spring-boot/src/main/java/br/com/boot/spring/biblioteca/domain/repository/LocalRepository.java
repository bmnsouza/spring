package br.com.boot.spring.biblioteca.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.biblioteca.domain.entity.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {
	
	@Query(nativeQuery = true)
	Slice<Local> buscar(Integer codigo, String nome, Pageable pageable);

}
