package com.rf.relatorio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rf.relatorio.entity.Equipe;
import com.rf.relatorio.exception.EquipeNotFoundException;
import com.rf.relatorio.repository.EquipeRepository;

@Service
public class EquipeService {
	
	private EquipeRepository equipeRepository;
	
	@Autowired
	public EquipeService(EquipeRepository equipeRepository) {
		this.equipeRepository = equipeRepository;
	}
	
	public Equipe createEquipe(Equipe equipe) {
		return equipeRepository.save(equipe);
	}
	
	@Transactional(readOnly = true)
	public List<Equipe> findAll() {
		return equipeRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Equipe findById(Long id) {
		return equipeRepository.findById(id).orElseThrow(() -> new EquipeNotFoundException(id));
	}

	@Transactional
	public void delete(Long id) {
		findById(id);
		equipeRepository.deleteById(id);
	}

	@Transactional
	public Equipe update(Long id, Equipe equipe) {
		Equipe equipeEncontrada = findById(id);
		//equipeEncontrada.setId(equipe.getId());
		equipeEncontrada.setNome(equipe.getNome());
		equipeRepository.save(equipeEncontrada);
		return equipeEncontrada;
	}
	
	
		
}
