package br.com.boot.spring.biblioteca.camadas.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.biblioteca.camadas.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, String> {
	
	@Query(nativeQuery = true)
	Slice<Livro> buscar(String isbn, String titulo, String subTitulo, Integer numPaginas, Integer edicao, Integer volume, Integer codEditora, Integer codLocal, Pageable pageable);

}
