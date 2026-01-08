package com.rf.relatorio.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rf.relatorio.dto.PermutasResumoDTO;
import com.rf.relatorio.entity.Relatorio;
import com.rf.relatorio.exception.RelatorioNotFoundException;
import com.rf.relatorio.repository.RelatorioRepository;

@Service
public class RelatorioService {
	
	private RelatorioRepository relatorioRepository;

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

	public List<Relatorio> buscarUltimos30Relatorios() {
			return relatorioRepository.findTop30ByOrderByIdDesc();
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
	

	public PermutasResumoDTO contarPermutasPorAgente(String nomeAgente, String dataInicio, String dataFim) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate inicio = LocalDate.parse(dataInicio, DateTimeFormatter.ISO_DATE);
		LocalDate fim = LocalDate.parse(dataFim, DateTimeFormatter.ISO_DATE);

		List<Relatorio> todosRelatorios = relatorioRepository.findAll();

		List<Relatorio> relatoriosNoPeriodo = todosRelatorios.stream()
				.filter(r -> {
						try {
								LocalDate data = LocalDate.parse(r.getDatadorelatorio(), formatter);
								return (data.isEqual(inicio) || data.isAfter(inicio)) &&
												(data.isEqual(fim) || data.isBefore(fim));
						} catch (Exception e) {
								return false;
						}
				})
				.toList();

		long solicitadas = relatoriosNoPeriodo.stream()
				.filter(r -> r.getAgentesparapermultar() != null)
				.map(Relatorio::getAgentesparapermultar)
				.flatMap(lista -> Arrays.stream(lista.split(",")))
				.map(String::trim)
				.filter(nome -> nome.equalsIgnoreCase(nomeAgente.trim()))
				.count();

		long realizadas = relatoriosNoPeriodo.stream()
				.filter(r -> r.getAgentedefolgaparapermultar() != null)
				.map(Relatorio::getAgentedefolgaparapermultar)
				.flatMap(lista -> Arrays.stream(lista.split(",")))
				.map(String::trim)
				.filter(nome -> nome.equalsIgnoreCase(nomeAgente.trim()))
				.count();

		return new PermutasResumoDTO(solicitadas, realizadas);
	}

}
