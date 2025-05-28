package com.rf.relatorio.dto;

import javax.validation.constraints.NotEmpty;

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
public class EscalaServicoDTO {
	
	
	private Long id;
	
	@NotEmpty
	private String data;
	
	@NotEmpty
	private Long equipeid;

}
