package com.rf.relatorio.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.rf.relatorio.dto.EquipeDTO;
import com.rf.relatorio.entity.Equipe;

@Component
public class EquipeMapper {
	private static final ModelMapper MODEL_MAPPER = new ModelMapper();
	
	public Equipe toEquipe(EquipeDTO dto) {
		return MODEL_MAPPER.map(dto, Equipe.class);
	}
	
	public EquipeDTO toEquipeDTO(Equipe equipe) {
		return MODEL_MAPPER.map(equipe, EquipeDTO.class);
	}
	
	public List<EquipeDTO> toEquipeDTOList(List<Equipe> list) {
		return list.stream().map(this::toEquipeDTO).collect(Collectors.toList());
	}
	

}
