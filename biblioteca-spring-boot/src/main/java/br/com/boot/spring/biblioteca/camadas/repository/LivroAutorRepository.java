package br.com.boot.spring.biblioteca.camadas.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.biblioteca.camadas.entity.LivroAutor;
import br.com.boot.spring.biblioteca.camadas.entity.id.LivroAutorId;

@Repository
public interface LivroAutorRepository extends JpaRepository<LivroAutor, LivroAutorId> {
	
	@Query(nativeQuery = true)
	Slice<LivroAutor> buscar(Integer codAutor, String isbnLivro, Pageable pageable);

}
