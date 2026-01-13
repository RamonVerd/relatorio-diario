package com.rf.relatorio.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rf.relatorio.entity.EscalaServico;
import com.rf.relatorio.exception.EscalaServicoNotFoundException;
import com.rf.relatorio.repository.EscalaServicoRepository;

@Service
public class EscalaServicoService {
	
	@Autowired
	private EscalaServicoRepository escalaServicoRepository;

	public EscalaServico createEscala(EscalaServico escala) {
		return escalaServicoRepository.save(escala);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<EscalaServico> findAll() {
		return escalaServicoRepository.findAll();
	}

  public List<EscalaServico> buscarUltimos30EscalaServico() {
			return escalaServicoRepository.findTop30ByOrderByIdDesc();
	}
	
	@Transactional(readOnly = true)
	public List<EscalaServico> listarEscalaDaEquipe(Long id) {
		return escalaServicoRepository.findByEquipeid(id);
	}
	
	@Transactional(readOnly = true)
	public EscalaServico findById(Long id) {
		return escalaServicoRepository.findById(id).orElseThrow(() -> new EscalaServicoNotFoundException(id));
	}
	
	@Transactional
	public EscalaServico update(Long id, EscalaServico escala) {
		EscalaServico equipeParaAtualizar = findById(id);
		equipeParaAtualizar.setEquipeid(escala.getEquipeid());
		equipeParaAtualizar.setData(escala.getData());
		escalaServicoRepository.save(equipeParaAtualizar);
		return equipeParaAtualizar;
	}
	
	
}
