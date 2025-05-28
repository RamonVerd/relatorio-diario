package com.rf.relatorio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rf.relatorio.dto.RelatorioDTO;
import com.rf.relatorio.entity.Relatorio;
import com.rf.relatorio.mapper.RelatorioMapper;
import com.rf.relatorio.service.RelatorioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/relatorio")
//@CrossOrigin(origins = "https://front-relatorio-v2.herokuapp.com/")
@CrossOrigin(origins = "*")
public class RelatorioController {

	
	private final RelatorioService relatorioService;
	
	private final RelatorioMapper relatorioMapper;

	@Autowired
	public RelatorioController(RelatorioService relatorioService, RelatorioMapper relatorioMapper) {
		this.relatorioService = relatorioService;
		this.relatorioMapper = relatorioMapper;
	}
	
	@GetMapping("/buscar-por-data")
    public ResponseEntity<Relatorio> buscarPorData(@RequestParam String data) {
        return relatorioService.buscarPorDataDoRelatorio(data)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
	@PostMapping
	@Operation(summary = "Criar novo relatório")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RelatorioDTO> createRelatorio(@RequestBody RelatorioDTO relatorioDto) {
		Relatorio relatorio = relatorioMapper.toRelatorio(relatorioDto);
		Relatorio createRelatorio = relatorioService.createRelatorio(relatorio);
		RelatorioDTO dto = relatorioMapper.toDTO(createRelatorio);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);					
	}
	
	@Operation(summary = "Listar todos Relatórios")
	@GetMapping
	public ResponseEntity<List<RelatorioDTO>> findAll(){
		List<Relatorio> list = relatorioService.findAll();
		List<RelatorioDTO> relatorioDTOList = relatorioMapper.toRelatorioDTOList(list);
		return ResponseEntity.ok(relatorioDTOList);
	}
	
	@Operation(summary = "Buscar relatório por id")
	@GetMapping("/{id}")
	public ResponseEntity<RelatorioDTO> findById(@PathVariable Long id) {
		Relatorio relatorio = relatorioService.findById(id);
		RelatorioDTO relatorioDTO = relatorioMapper.toDTO(relatorio);
		return ResponseEntity.ok(relatorioDTO);
	}
	
	@Operation(summary = "Deletar relatório")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		relatorioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Atualizar relatório")
	@PutMapping("/{id}")
	public ResponseEntity<RelatorioDTO> update(@PathVariable Long id, @RequestBody RelatorioDTO relatorioDTO) {
		Relatorio relatorioAtualizada = relatorioMapper.toRelatorio(relatorioDTO);
		Relatorio pessoa = relatorioService.update(id, relatorioAtualizada);
		return ResponseEntity.ok(relatorioMapper.toDTO(pessoa));
	}
	
	
}
