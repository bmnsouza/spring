package br.com.boot.spring.oxeconfeitaria.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.boot.spring.base.util.CacheUtil;
import br.com.boot.spring.oxeconfeitaria.domain.entity.Insumo;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Integer> {
	
	@Cacheable(value = CacheUtil.CACHE_OXECONFEITARIA, key = "{'InsumoRepository_getByIdInsumo', #idInsumo}", unless = "#result.isEmpty()")
	Optional<List<Insumo>> getByIdInsumo(Integer idInsumo);
	
	@Cacheable(value = CacheUtil.CACHE_OXECONFEITARIA, key = "{'InsumoRepository_findByDsInsumoContainingIgnoreCase', #idInsumo, #sort}", unless = "#result.isEmpty()")
	Optional<List<Insumo>> findByDsInsumoContainingIgnoreCase(String dsInsumo, Sort sort);
	
	@Query(nativeQuery = true)
	@Cacheable(value = CacheUtil.CACHE_OXECONFEITARIA, key = "{'InsumoRepository_buscarNativeQuery', #idInsumo, #dsInsumo}", unless = "#result.isEmpty()")
	Optional<List<Insumo>> buscarNativeQuery(Integer idInsumo, String dsInsumo);

}
