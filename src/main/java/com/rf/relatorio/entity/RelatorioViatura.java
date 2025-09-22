package com.rf.relatorio.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioViatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String modelo;
    private String motorista;
    private LocalDate data;
    private Integer kmInicial;
    private Integer kmFinal;
    private Integer kmRodada;
    private LocalTime horaRetorno;
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
