package br.com.boot.spring.biblioteca.domain.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.base.util.CacheUtil;
import br.com.boot.spring.biblioteca.domain.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
	
	@Query(nativeQuery = true)
	@Cacheable(value = CacheUtil.CACHE_BIBLIOTECA, key = "{'AutorRepository_buscar', #codigo, #primeiroNome, #inicialMeioNome, #ultimoNome, #pageable.getPageNumber()}", unless = "#result.isEmpty()")
	Slice<Autor> buscar(Integer codigo, String primeiroNome, String inicialMeioNome, String ultimoNome, Pageable pageable);

}
