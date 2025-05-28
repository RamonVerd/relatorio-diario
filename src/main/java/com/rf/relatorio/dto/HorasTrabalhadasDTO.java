package com.rf.relatorio.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class HorasTrabalhadasDTO {
    private long horas;
    private long minutos;
    private int totalFaltas;
    
    public HorasTrabalhadasDTO(long horas, long minutos, int totalFaltas) {
        this.horas = horas;
        this.minutos = minutos;
        this.totalFaltas = totalFaltas;
    }
    
}
