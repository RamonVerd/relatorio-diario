package com.rf.relatorio.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rf.relatorio.entity.EscalaServico;

public interface EscalaServicoRepository extends JpaRepository<EscalaServico, Long>{

	List<EscalaServico> findByEquipeid(Long id);

	// Busca os 30 últimos escala_servico pelo id em ordem decrescente
	@Query(value = "SELECT * FROM escala_servico ORDER BY id DESC LIMIT 30", nativeQuery = true)
	List<EscalaServico> findTop30ByOrderByIdDesc();

}
