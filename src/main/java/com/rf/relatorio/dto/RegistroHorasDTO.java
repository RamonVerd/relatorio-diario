package com.rf.relatorio.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistroHorasDTO {
	private Long agente_id;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private int atraso;
    private boolean falta;
    private String justificativaFalta;
}
