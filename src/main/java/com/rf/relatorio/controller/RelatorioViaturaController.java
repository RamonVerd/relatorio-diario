package com.rf.relatorio.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rf.relatorio.dto.RelatorioViaturaDTO;
import com.rf.relatorio.dto.ResumoMensalDTO;
import com.rf.relatorio.entity.RelatorioViatura;
import com.rf.relatorio.mapper.RelatorioViaturaMapper;
import com.rf.relatorio.service.RelatorioViaturaService;

@RestController
@RequestMapping("/api/v1/relatorios-viatura")
@CrossOrigin(origins = "*")
public class RelatorioViaturaController {

    @Autowired
    private RelatorioViaturaService service;

    @Autowired
    private RelatorioViaturaMapper mapper;

    @GetMapping
    public ResponseEntity<List<RelatorioViaturaDTO>> listar(
            @RequestParam(required = false) LocalDate inicio,
            @RequestParam(required = false) LocalDate fim) {
        var lista = service.listarPorPeriodo(inicio, fim);
        return ResponseEntity.ok(mapper.toDTOList(lista));
    }

    @PostMapping
    public ResponseEntity<RelatorioViaturaDTO> salvar(@RequestBody RelatorioViaturaDTO dto) {
        RelatorioViatura entidade = mapper.toEntity(dto);
        
        RelatorioViatura salvo = service.save(entidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(salvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioViaturaDTO> buscarPorId(@PathVariable Long id) {
        return service.findById(id)
            .map(mapper::toDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exportar-pdf")
    public ResponseEntity<byte[]> exportarPdf(
            @RequestParam(required = false) LocalDate inicio,
            @RequestParam(required = false) LocalDate fim) {
        byte[] pdf = service.gerarPdf(inicio, fim);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=relatorio-viaturas.pdf")
                .header("Content-Type", "application/pdf")
                .body(pdf);
    }

    @GetMapping("/stats/mensal")
    public ResponseEntity<List<ResumoMensalDTO>> resumoMensal(
            @RequestParam(required = false) LocalDate inicio,
            @RequestParam(required = false) LocalDate fim) {
        return ResponseEntity.ok(service.resumoMensal(inicio, fim));
    }

}
