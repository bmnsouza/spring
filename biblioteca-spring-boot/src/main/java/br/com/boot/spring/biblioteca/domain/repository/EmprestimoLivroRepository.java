package br.com.boot.spring.biblioteca.domain.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.base.util.CacheUtil;
import br.com.boot.spring.biblioteca.domain.entity.EmprestimoLivro;
import br.com.boot.spring.biblioteca.domain.entity.id.EmprestimoLivroId;

@Repository
public interface EmprestimoLivroRepository extends JpaRepository<EmprestimoLivro, EmprestimoLivroId> {
	
	@Query(nativeQuery = true)
	@Cacheable(value = CacheUtil.CACHE_BIBLIOTECA, key = "{'EmprestimoLivroRepository_buscar', #numEmprestimo, #isbnLivro, #dataPrevDev, #dataDev, #valorMulta, #pageable.getPageNumber()}", unless = "#result.isEmpty()")	
	Slice<EmprestimoLivro> buscar(Integer numEmprestimo, String isbnLivro, LocalDate dataPrevDev, LocalDate dataDev, BigDecimal valorMulta, Pageable pageable);

}
