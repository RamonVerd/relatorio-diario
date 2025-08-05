package com.rf.relatorio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumoHorasDTO {
    private String nome;
    private String cargo;
    private long totalHoras;
    private long minutosRestantes;
    private int faltas;
    private long permutasSolicitadas;
    private long permutasRealizadas;
}
