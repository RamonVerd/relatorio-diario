package com.rf.relatorio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rf.relatorio.entity.Relatorio;
import com.rf.relatorio.exception.RelatorioNotFoundException;
import com.rf.relatorio.repository.RelatorioRepository;

@Service
public class RelatorioService {
	
	private RelatorioRepository relatorioRepository;

	@Autowired
	public RelatorioService(RelatorioRepository relatorioRepository) {
		this.relatorioRepository = relatorioRepository;
	}
	
	public Optional<Relatorio> buscarPorDataDoRelatorio(String data) {
	  return relatorioRepository.findByDatadorelatorio(data);
	}
	
	public Relatorio createRelatorio(Relatorio relatorio) {
		return  relatorioRepository.save(relatorio); 
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Relatorio> findAll() {
		return relatorioRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Relatorio findById(Long id) {
		return relatorioRepository.findById(id).orElseThrow(() -> new RelatorioNotFoundException(id));
	}
	
	@Transactional
	public void delete(Long id) {
		findById(id);
		relatorioRepository.deleteById(id);
	}
	
	@Transactional
	public Relatorio update(Long id, Relatorio relatorio) {
		Relatorio relatorioUpdate = findById(id);
		relatorioUpdate.setAgentedefolgaparapermultar(relatorio.getAgentedefolgaparapermultar());
		relatorioUpdate.setAgentesparapermultar(relatorio.getAgentesparapermultar());
		relatorioUpdate.setNomeinspetor(relatorio.getNomeinspetor());
		relatorioUpdate.setNomeequipe(relatorio.getNomeequipe());
		relatorioUpdate.setDatadehoje(relatorio.getDatadehoje());
		relatorioUpdate.setDatadorelatorio(relatorio.getDatadorelatorio());
		relatorioUpdate.setAgentesdaequipe(relatorio.getAgentesdaequipe());
		relatorioUpdate.setAlteracao(relatorio.getAlteracao());
		relatorioUpdate.setAgentesfaltoso(relatorio.getAgentesfaltoso());
		relatorioUpdate.setAgentesparareforco(relatorio.getAgentesparareforco());
		relatorioUpdate.setTexto1(relatorio.getTexto1());
		relatorioUpdate.setTexto2(relatorio.getTexto2());
		relatorioUpdate.setTexto3(relatorio.getTexto3());
		
		relatorioRepository.save(relatorioUpdate);
		return relatorioUpdate;
	}
	

}
