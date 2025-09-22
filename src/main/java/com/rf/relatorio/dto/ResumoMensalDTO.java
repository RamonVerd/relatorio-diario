package com.rf.relatorio.dto;

import java.time.YearMonth;

public class ResumoMensalDTO {

  private YearMonth mes;
    private Long kmRodadoTotal;
    private Double litrosTotal;

    public ResumoMensalDTO(YearMonth mes, Long kmRodadoTotal, Double litrosTotal) {
        this.mes = mes;
        this.kmRodadoTotal = kmRodadoTotal;
        this.litrosTotal = litrosTotal;
    }

    public YearMonth getMes() { return mes; }
    public void setMes(YearMonth mes) { this.mes = mes; }

    public Long getKmRodadoTotal() { return kmRodadoTotal; }
    public void setKmRodadoTotal(Long kmRodadoTotal) { this.kmRodadoTotal = kmRodadoTotal; }

    public Double getLitrosTotal() { return litrosTotal; }
    public void setLitrosTotal(Double litrosTotal) { this.litrosTotal = litrosTotal; }
}
