package com.rf.relatorio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rf.relatorio.entity.Relatorio;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long>{
	
	 Optional<Relatorio> findByDatadorelatorio(String datadorelatorio);

}
