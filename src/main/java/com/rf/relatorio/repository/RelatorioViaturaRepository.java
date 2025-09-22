package com.rf.relatorio.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rf.relatorio.entity.RelatorioViatura;

public interface RelatorioViaturaRepository extends JpaRepository<RelatorioViatura, Long>{

  List<RelatorioViatura> findByDataBetween(LocalDate inicio, LocalDate fim);
}
