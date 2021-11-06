package br.com.boot.spring.biblioteca.domain.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.base.util.CacheUtil;
import br.com.boot.spring.biblioteca.domain.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, String> {
	
	@Query(nativeQuery = true)
	@Cacheable(value = CacheUtil.CACHE_BIBLIOTECA, key = "{'LivroRepository_buscar', #isbn, #titulo, #subTitulo, #numPaginas, #edicao, #volume, #codEditora, #codLocal, #pageable.getPageNumber()}", unless = "#result.isEmpty()")		
	Slice<Livro> buscar(String isbn, String titulo, String subTitulo, Integer numPaginas, Integer edicao, Integer volume, Integer codEditora, Integer codLocal, Pageable pageable);

}
