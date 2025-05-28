package com.rf.relatorio.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.rf.relatorio.dto.EscalaServicoDTO;
import com.rf.relatorio.entity.EscalaServico;

@Component
public class EscalaServicoMapper {
private static final ModelMapper MODEL_MAPPER = new ModelMapper();
	
	public EscalaServico toEscalaServico(EscalaServicoDTO dto) {
		return MODEL_MAPPER.map(dto, EscalaServico.class);
	}
	
	public EscalaServicoDTO toEscalaServicoDTO(EscalaServico escalaServico) {
		return MODEL_MAPPER.map(escalaServico, EscalaServicoDTO.class);
	}
	
	public List<EscalaServicoDTO> toEscalaServicoDTOList(List<EscalaServico> list) {
		return list.stream().map(this::toEscalaServicoDTO).collect(Collectors.toList());
	}
}
