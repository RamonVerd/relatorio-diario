package com.rf.relatorio.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rf.relatorio.entity.AgenteUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EquipeDTO {
	
	private Long id;
	
	@NotEmpty
	private String nome;
	
	private List<AgenteUser> membros;
	
//	@NotEmpty
//	private String id_agentes;

}
