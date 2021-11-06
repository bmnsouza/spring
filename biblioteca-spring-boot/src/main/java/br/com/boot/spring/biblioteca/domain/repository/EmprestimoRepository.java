package br.com.boot.spring.biblioteca.domain.repository;

import java.time.LocalDate;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.base.util.CacheUtil;
import br.com.boot.spring.biblioteca.domain.entity.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
	
	@Query(nativeQuery = true)
	@Cacheable(value = CacheUtil.CACHE_BIBLIOTECA, key = "{'EmprestimoRepository_buscar', #numero, #matUsuario, #data, #pageable.getPageNumber()}", unless = "#result.isEmpty()")
	Slice<Emprestimo> buscar(Integer numero, Integer matUsuario, LocalDate data, Pageable pageable);

}
