package br.com.boot.spring.biblioteca.domain.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.biblioteca.domain.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query(nativeQuery = true)
	Slice<Usuario> buscar(Integer matricula, String nome, String cpf, String endereco, String telefone, BigDecimal renda, Pageable pageable);

}
