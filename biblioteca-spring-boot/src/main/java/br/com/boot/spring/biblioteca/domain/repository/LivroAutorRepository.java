package br.com.boot.spring.biblioteca.domain.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.base.util.CacheUtil;
import br.com.boot.spring.biblioteca.domain.entity.LivroAutor;
import br.com.boot.spring.biblioteca.domain.entity.id.LivroAutorId;

@Repository
public interface LivroAutorRepository extends JpaRepository<LivroAutor, LivroAutorId> {
	
	@Query(nativeQuery = true)
	@Cacheable(value = CacheUtil.CACHE_BIBLIOTECA, key = "{'LivroAutorRepository_buscar', #codAutor, #isbnLivro, #pageable.getPageNumber()}", unless = "#result.isEmpty()")	
	Slice<LivroAutor> buscar(Integer codAutor, String isbnLivro, Pageable pageable);

}
