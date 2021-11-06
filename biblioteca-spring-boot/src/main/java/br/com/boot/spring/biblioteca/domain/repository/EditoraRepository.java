package br.com.boot.spring.biblioteca.domain.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.base.util.CacheUtil;
import br.com.boot.spring.biblioteca.domain.entity.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Integer> {
	
	@Query(nativeQuery = true)
	@Cacheable(value = CacheUtil.CACHE_BIBLIOTECA, key = "{'EditoraRepository_buscar', #codigo, #nome, #pageable.getPageNumber()}", unless = "#result.isEmpty()")
	Slice<Editora> buscar(Integer codigo, String nome, Pageable pageable);

}
