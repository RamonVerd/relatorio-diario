package com.rf.relatorio.service;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rf.relatorio.dto.HorasTrabalhadasDTO;
import com.rf.relatorio.entity.RegistroHoras;
import com.rf.relatorio.exception.RegistroHorasNotFoundException;
import com.rf.relatorio.repository.RegistroHorasRepository;



@Service
public class HorasTrabalhadasService {

	@Autowired
	private RegistroHorasRepository registroHorasRepository;	    
    
    public RegistroHoras salvarRegistroHoras(RegistroHoras registroHoras) {
       return registroHorasRepository.save(registroHoras);
    }
    
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RegistroHoras> buscarTodosRegistroHorasRepository() {
		return registroHorasRepository.findAll();
	}
    
    public List<RegistroHoras> getRegistrosByAgenteIdAndPeriodo(
    		Long agenteId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return registroHorasRepository.findByAgenteIdAndDataHoraInicioBetween(agenteId, dataInicio, dataFim);
    }
    
    @Transactional(readOnly = true)
	public RegistroHoras findById(Long id) {
		return registroHorasRepository.findById(id).orElseThrow(() -> new RegistroHorasNotFoundException(id));
	}
    
    @Transactional
	public void delete(Long id) {
		findById(id);
		registroHorasRepository.deleteById(id);
	}
    
    @Transactional
   	public RegistroHoras update(RegistroHoras registro, Long id) {
    	RegistroHoras registroHorasUpdate = findById(id);
    	registroHorasUpdate.setAgente(registro.getAgente());
    	registroHorasUpdate.setDataHoraInicio(registro.getDataHoraInicio());
    	registroHorasUpdate.setDataHoraFim(registro.getDataHoraFim());
    	registroHorasUpdate.setAtraso(registro.getAtraso());
    	registroHorasUpdate.setFalta(registro.isFalta());
    	registroHorasUpdate.setJustificativaFalta(registro.getJustificativaFalta());
   		return registroHorasUpdate;
   	}
    
    
    public HorasTrabalhadasDTO calcularHorasTrabalhadas(Long agenteId, LocalDateTime inicio, LocalDateTime fim) {
        List<RegistroHoras> registros = registroHorasRepository.findByAgente_IdAndDataHoraInicioBetween(agenteId, inicio, fim);

        long totalMinutosTrabalhados = 0;
        int totalFaltas = 0;

        for (RegistroHoras registro : registros) {
            if (registro.isFalta()) {
                totalFaltas++; // Conta quantas vezes o agente faltou
            } else if (registro.getDataHoraInicio() != null && registro.getDataHoraFim() != null) {
                // Calcula o tempo trabalhado em minutos
                long minutosTrabalhados = Duration.between(registro.getDataHoraInicio(), registro.getDataHoraFim()).toMinutes();

                // Subtrai os minutos de atraso
                minutosTrabalhados -= registro.getAtraso();

                // Se o atraso for maior que o tempo trabalhado, considera zero
                if (minutosTrabalhados < 0) {
                    minutosTrabalhados = 0;
                }

                totalMinutosTrabalhados += minutosTrabalhados;
            }
        }

        // Calcula horas e minutos exatos
        long horas = totalMinutosTrabalhados / 60;
        long minutos = totalMinutosTrabalhados % 60;

        return new HorasTrabalhadasDTO(horas, minutos, totalFaltas);
    }

    
    

    
    

    
  
  
    
    
}
