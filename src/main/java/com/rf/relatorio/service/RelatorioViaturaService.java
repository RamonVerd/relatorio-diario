package com.rf.relatorio.service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rf.relatorio.dto.ResumoMensalDTO;
import com.rf.relatorio.entity.RelatorioViatura;
import com.rf.relatorio.repository.RelatorioViaturaRepository;

@Service
public class RelatorioViaturaService {

    @Autowired
    private RelatorioViaturaRepository repository;

    public List<RelatorioViatura> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<RelatorioViatura> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null) {
            return repository.findAll();
        }
        return repository.findByDataBetween(inicio, fim);
    }

    public RelatorioViatura save(RelatorioViatura relatorio) {
        // validar(relatorio);
        return repository.save(relatorio);
    }

    public Optional<RelatorioViatura> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // ---------- VALIDAÇÃO DE NEGÓCIO ----------

    // private void validar(RelatorioViatura r) {
    //     Objects.requireNonNull(r.getPlaca(), "Placa é obrigatória");
    //     Objects.requireNonNull(r.getModelo(), "Modelo é obrigatório");
    //     Objects.requireNonNull(r.getMotorista(), "Motorista é obrigatório");
    //     Objects.requireNonNull(r.getData(), "Data é obrigatória");
    //     Objects.requireNonNull(r.getKmInicial(), "KM inicial é obrigatório");
    //     Objects.requireNonNull(r.getKmFinal(), "KM final é obrigatório");

    //     if (r.getKmInicial() < 0 || r.getKmFinal() < 0) {
    //         throw new IllegalArgumentException("KM não pode ser negativo");
    //     }
    //     if (r.getKmFinal() < r.getKmInicial()) {
    //         throw new IllegalArgumentException("KM final não pode ser menor que o KM inicial");
    //     }
    //     if (r.getAbastecimentoLitros() != null && r.getAbastecimentoLitros() < 0) {
    //         throw new IllegalArgumentException("Abastecimento (litros) não pode ser negativo");
    //     }
    // }

     // ---------- EXPORTAÇÃO EM PDF ----------

    @Transactional(readOnly = true)
    public byte[] gerarPdf(LocalDate inicio, LocalDate fim) {
        List<RelatorioViatura> dados = listarPorPeriodo(inicio, fim);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document doc = new Document(PageSize.A4.rotate(), 24, 24, 24, 24); // paisagem p/ caber a tabela
        try {
            PdfWriter.getInstance(doc, out);
            doc.open();

            String titulo = "Relatório de Viaturas";
            if (inicio != null && fim != null) {
                titulo += " — Período: " + df.format(inicio) + " a " + df.format(fim);
            }

            Paragraph header = new Paragraph(titulo, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
            header.setSpacingAfter(12f);
            doc.add(header);

            PdfPTable table = new PdfPTable(new float[]{14, 12, 18, 10, 11, 11, 10, 18, 18, 10});
            table.setWidthPercentage(100);

            // Cabeçalho
            addCellHeader(table, "Data");
            addCellHeader(table, "Placa");
            addCellHeader(table, "Motorista");
            addCellHeader(table, "KM Ini");
            addCellHeader(table, "KM Fim");
            addCellHeader(table, "Rodado");
            addCellHeader(table, "Litros");
            addCellHeader(table, "Destino");
            addCellHeader(table, "Finalidade");
            addCellHeader(table, "Obs");

            // Linhas
            for (RelatorioViatura r : dados) {
                addCell(table, r.getData() != null ? df.format(r.getData()) : "-");
                addCell(table, safe(r.getPlaca()));
                addCell(table, safe(r.getMotorista()));
                addCell(table, num(r.getKmInicial()));
                addCell(table, num(r.getKmFinal()));
                addCell(table, num(r.getKmRodada()));
                addCell(table, num(r.getAbastecimentoLitros()));
                addCell(table, safe(r.getDestino()));
                addCell(table, safe(r.getFinalidade()));
                addCell(table, safe(r.getObservacoes()));
            }

            doc.add(table);

            // Rodapé com totais
            int totalKm = dados.stream().map(RelatorioViatura::getKmRodada).filter(Objects::nonNull).mapToInt(Integer::intValue).sum();
            double totalLitros = dados.stream().map(RelatorioViatura::getAbastecimentoLitros).filter(Objects::nonNull).mapToDouble(Double::doubleValue).sum();

            Paragraph totals = new Paragraph(
                "\nTotais no período — KM: " + totalKm + " | Litros: " + String.format(Locale.US, "%.2f", totalLitros),
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)
            );
            doc.add(totals);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        } finally {
            doc.close();
        }
        return out.toByteArray();
    }

    private static void addCellHeader(PdfPTable t, String text) {
        var f = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        t.addCell(new Phrase(text, f));
    }

    private static void addCell(PdfPTable t, String text) {
        var f = FontFactory.getFont(FontFactory.HELVETICA, 10);
        t.addCell(new Phrase(text == null ? "-" : text, f));
    }

    private static String num(Number n) {
        return n == null ? "-" : String.valueOf(n);
    }

    private static String safe(String s) {
        return (s == null || s.isBlank()) ? "-" : s;
    }

     // ---------- RESUMO MENSAL (para os gráficos) ----------

    @Transactional(readOnly = true)
    public List<ResumoMensalDTO> resumoMensal(LocalDate inicio, LocalDate fim) {
        List<RelatorioViatura> lista = listarPorPeriodo(inicio, fim);
        Map<YearMonth, ResumoMensalDTO> mapa = new TreeMap<>();

        for (RelatorioViatura r : lista) {
            if (r.getData() == null) continue;
            YearMonth ym = YearMonth.from(r.getData());
            mapa.putIfAbsent(ym, new ResumoMensalDTO(ym, 0L, 0d));

            ResumoMensalDTO acc = mapa.get(ym);
            long km = Optional.ofNullable(r.getKmRodada()).orElse(0);
            double litros = Optional.ofNullable(r.getAbastecimentoLitros()).orElse(0d);

            acc.setKmRodadoTotal(acc.getKmRodadoTotal() + km);
            acc.setLitrosTotal(acc.getLitrosTotal() + litros);
        }

        return new ArrayList<>(mapa.values());
    }

}
