package com.rf.relatorio.dto;

import javax.validation.constraints.NotEmpty;

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
public class RelatorioDTO {

	private Long id;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private String datadorelatorio;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private String datadehoje;
	
	@NotEmpty
	private String alteracao;

	@NotEmpty
	private String nomeequipe;

	@NotEmpty
	private String nomeinspetor;

	@NotEmpty
	private String agentesdaequipe;

	@NotEmpty
	private String agentesparapermultar;

	@NotEmpty
	private String agentedefolgaparapermultar;

	@NotEmpty
	private String agentesparareforco;
	
	@NotEmpty
	private String agentesfaltoso;

	@NotEmpty
	private String texto1;

	@NotEmpty
	private String texto2;

	@NotEmpty
	private String texto3;


}


