package com.rf.relatorio.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.rf.relatorio.dto.AgenteUserDTO;
import com.rf.relatorio.entity.AgenteUser;

@Component
public class AgenteUserMapper {
	
	private static final ModelMapper MODEL_MAPPER = new ModelMapper();
	
	public AgenteUser toAgenteUser(AgenteUserDTO dto) {
		return MODEL_MAPPER.map(dto, AgenteUser.class);
	}
	
	public AgenteUserDTO toDTO(AgenteUser agenteUser) {
		return MODEL_MAPPER.map(agenteUser, AgenteUserDTO.class);
	}

	public List<AgenteUserDTO> toAgenteUserDTOList(List<AgenteUser> list) {
		return list.stream().map(this::toDTO).collect(Collectors.toList());
		
		//return relatorioList.stream().map(this::toDTO).collect(Collectors.toList());
	}

}


