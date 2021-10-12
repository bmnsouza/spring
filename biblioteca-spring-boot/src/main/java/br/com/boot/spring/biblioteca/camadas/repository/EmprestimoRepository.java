package br.com.boot.spring.biblioteca.camadas.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.biblioteca.camadas.entity.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
	
	@Query(nativeQuery = true)
	Slice<Emprestimo> buscar(Integer numero, Integer matUsuario, LocalDate data, Pageable pageable);

}
