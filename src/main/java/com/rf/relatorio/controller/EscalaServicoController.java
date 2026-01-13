package com.rf.relatorio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rf.relatorio.dto.EscalaServicoDTO;
import com.rf.relatorio.entity.EscalaServico;
import com.rf.relatorio.mapper.EscalaServicoMapper;
import com.rf.relatorio.service.EscalaServicoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/escala-servico")
@CrossOrigin(origins = "*")
public class EscalaServicoController {
	
	@Autowired
	private EscalaServicoService escalaServicoService;

	@Autowired
	private EscalaServicoMapper escalaServicoMapper;
	
	@Operation(summary = "Criar escala")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<EscalaServicoDTO> createEscala(@RequestBody EscalaServicoDTO escalaDto) {
		EscalaServico escala = escalaServicoMapper.toEscalaServico(escalaDto);
		EscalaServico escalaCriada = escalaServicoService.createEscala(escala);
		
		EscalaServicoDTO escalaCriadaDto = escalaServicoMapper.toEscalaServicoDTO(escalaCriada);
		return ResponseEntity.status(HttpStatus.CREATED).body(escalaCriadaDto);
		
	}

	@Operation(summary = "Editar escala")
	@PutMapping("/{id}")
	public ResponseEntity<EscalaServicoDTO> update(@PathVariable Long id, @RequestBody EscalaServicoDTO escalaServicoDTO) {
		EscalaServico escala = escalaServicoMapper.toEscalaServico(escalaServicoDTO);
		escalaServicoService.update(id, escala);
		return ResponseEntity.ok(escalaServicoMapper.toEscalaServicoDTO(escala));
	}
	
	@Operation(summary = "Buscar todas as escalas")
	@GetMapping
	public ResponseEntity<List<EscalaServicoDTO>> findAll(){
		List<EscalaServico> list = escalaServicoService.findAll();
		List<EscalaServicoDTO> escalaServicoDtoList = escalaServicoMapper.toEscalaServicoDTOList(list);
		
		return ResponseEntity.ok(escalaServicoDtoList);
	}

	@GetMapping("/ultimos30")
	public List<EscalaServico> getUltimos30EscalaServico() {
			return escalaServicoService.buscarUltimos30EscalaServico();
	}
	
	@Operation(summary = "Buscar escala por id")
	@GetMapping("/{id}")
	public ResponseEntity<EscalaServicoDTO> findById(@PathVariable Long id) {
		EscalaServico escalaServico = escalaServicoService.findById(id);
		EscalaServicoDTO escalaDto = escalaServicoMapper.toEscalaServicoDTO(escalaServico);
		
		return ResponseEntity.ok(escalaDto);
	}
	
	@Operation(summary = "Buscar escala da equipe")
	@GetMapping("/equipe/{id}")
	public ResponseEntity<List<EscalaServicoDTO>> buscarPorEquipe(@PathVariable Long id) {
		List<EscalaServico> list = escalaServicoService.listarEscalaDaEquipe(id);
		List<EscalaServicoDTO> escalasDaEquipeDto = escalaServicoMapper.toEscalaServicoDTOList(list);
		
		return ResponseEntity.ok(escalasDaEquipeDto);
		
	}

}
