package com.rf.relatorio.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rf.relatorio.entity.EscalaServico;

public interface EscalaServicoRepository extends JpaRepository<EscalaServico, Long>{

	List<EscalaServico> findByEquipeid(Long id);

}
