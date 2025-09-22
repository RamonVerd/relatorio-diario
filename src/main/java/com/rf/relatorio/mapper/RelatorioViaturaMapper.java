package com.rf.relatorio.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.rf.relatorio.dto.RelatorioViaturaDTO;
import com.rf.relatorio.entity.RelatorioViatura;

@Component
public class RelatorioViaturaMapper {

    public RelatorioViaturaDTO toDTO(RelatorioViatura entity) {
        if (entity == null) return null;

        RelatorioViaturaDTO dto = new RelatorioViaturaDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setKmRodada(entity.getKmRodada());
        return dto;
    }

    public RelatorioViatura toEntity(RelatorioViaturaDTO dto) {
        if (dto == null) return null;

        RelatorioViatura entity = new RelatorioViatura();
        BeanUtils.copyProperties(dto, entity);
        // entity.setKmRodada(dto.getKmRodada()); // aqui você força o valor
        return entity;
    }

    public List<RelatorioViaturaDTO> toDTOList(List<RelatorioViatura> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
