package com.rf.relatorio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rf.relatorio.entity.Relatorio;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long>{
	
	Optional<Relatorio> findByDatadorelatorio(String datadorelatorio);

	@Query("SELECT r FROM Relatorio r WHERE r.datadorelatorio BETWEEN :inicio AND :fim")
	List<Relatorio> findByPeriodo(@Param("inicio") String inicio, @Param("fim") String fim);


}
