package com.rf.relatorio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rf.relatorio.entity.AgenteUser;

public interface AgenteUserRepository extends JpaRepository<AgenteUser, Long>{

	List<AgenteUser> findByEquipeId(Long equipeId);
	
	List<AgenteUser> findByEquipeIsNull();
}
