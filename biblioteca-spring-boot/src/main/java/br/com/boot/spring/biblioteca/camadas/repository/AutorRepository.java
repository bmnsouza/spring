package br.com.boot.spring.biblioteca.camadas.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.biblioteca.camadas.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
	
	@Query(nativeQuery = true)
	Slice<Autor> buscar(Integer codigo, String primeiroNome, String inicialMeioNome, String ultimoNome, Pageable pageable);

}
