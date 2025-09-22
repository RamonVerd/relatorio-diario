package com.rf.relatorio.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class RelatorioViaturaDTO {

    private Long id;
    private String placa;
    private String modelo;
    private String motorista;
    private LocalDate data;
    private Integer kmInicial;
    private Integer kmFinal;
    private Integer kmRodada;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horaRetorno;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horaSaida;
    private String destino;
    private String finalidade;
    private Double abastecimentoLitros;
    private String observacoes;

    public Integer getKmRodada() {
        if (kmInicial != null && kmFinal != null) {
            return kmFinal - kmInicial;
        }
        return null;
    } 


}
