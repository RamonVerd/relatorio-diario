package com.rf.relatorio.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.rf.relatorio.dto.RegistroHorasDTO;
import com.rf.relatorio.entity.RegistroHoras;

@Component
public class HoraTrabalhadasMapper {
	
	private static final ModelMapper MODEL_MAPPER = new ModelMapper();
	
	
	public RegistroHoras toRegistroHoras(RegistroHorasDTO dto) {
		return MODEL_MAPPER.map(dto, RegistroHoras.class);
	}
	
	public RegistroHorasDTO toRegistroHorasDTO(RegistroHoras registroHoras) {
		return MODEL_MAPPER.map(registroHoras, RegistroHorasDTO.class);
	}
	
	public List<RegistroHorasDTO> toRegistroHorasDTOList(List<RegistroHoras> list) {
		return list.stream().map(this::toRegistroHorasDTO).collect(Collectors.toList());
	}

}
