package br.com.boot.spring.biblioteca.camadas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.biblioteca.camadas.entity.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Integer> {
	
	@Query(nativeQuery = true)
	List<Editora> buscar(Integer codigo, String nome);

}
