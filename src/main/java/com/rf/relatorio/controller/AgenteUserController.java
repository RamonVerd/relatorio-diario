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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rf.relatorio.dto.AgenteUserDTO;
import com.rf.relatorio.entity.AgenteUser;
import com.rf.relatorio.mapper.AgenteUserMapper;
import com.rf.relatorio.service.AgenteUserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/agenteUser")
//@CrossOrigin(origins = "http://localhost:4200/")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "https://front-relatorio-v2.herokuapp.com/")
public class AgenteUserController {
	
	private AgenteUserService agenteService;
	private AgenteUserMapper agenteUserMapper;
	
	@Autowired
	public AgenteUserController(AgenteUserService agenteService, AgenteUserMapper agenteUserMapper) {
		this.agenteService = agenteService;
		this.agenteUserMapper = agenteUserMapper;
	}
	
	@Operation(summary = "Adicionar novo agente")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AgenteUserDTO> createAgenteUser(@RequestBody AgenteUserDTO userDto) {
		AgenteUser agenteUser = agenteUserMapper.toAgenteUser(userDto);
		AgenteUser agente = agenteService.createAgenteUser(agenteUser);
		AgenteUserDTO dto = agenteUserMapper.toDTO(agente);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@Operation(summary = "Buscar todos os agentes")
	@GetMapping
	public ResponseEntity<List<AgenteUserDTO>> findAll(){
		List<AgenteUser> list = agenteService.findAll();
		List<AgenteUserDTO> agenteUserDTOList = agenteUserMapper.toAgenteUserDTOList(list);
		return ResponseEntity.ok(agenteUserDTOList);
	}
	
	@Operation(summary = "Buscar agente por id")
	@GetMapping("/{id}")
	public ResponseEntity<AgenteUserDTO> findById(@PathVariable Long id) {
		AgenteUser agenteUser = agenteService.findById(id);
		AgenteUserDTO agenteUserDTO = agenteUserMapper.toDTO(agenteUser);
		return ResponseEntity.ok(agenteUserDTO);
	}
	
	@Operation(summary = "Deletar agente")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		agenteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Editar agente")
	@PutMapping("/{id}")
	public ResponseEntity<AgenteUserDTO> update(@PathVariable Long id, @RequestBody AgenteUserDTO agenteUserDTO) {
		AgenteUser agenteUser = agenteUserMapper.toAgenteUser(agenteUserDTO);
		AgenteUser user = agenteService.update(id, agenteUser);
		return ResponseEntity.ok(agenteUserMapper.toDTO(user));
	}
	
    @PutMapping("/{agenteId}/adicionar-equipe/{equipeId}")
    public String adicionarAgenteAEquipe(
            @PathVariable Long agenteId,
            @PathVariable Long equipeId) {
        return agenteService.adicionarAgenteAEquipe(agenteId, equipeId);
    }
    
    // Endpoint para remover um AgenteUser de uma equipe
    @PutMapping("/{agenteId}/remover-equipe")
    public String removerAgenteDeEquipe(@PathVariable Long agenteId) {
        return agenteService.removerAgenteDeEquipe(agenteId);
    }
    
    @GetMapping("/sem-equipe")
    public List<AgenteUser> listarAgentesSemEquipe() {
        return agenteService.buscarAgentesSemEquipe();
    }
	
	
}
