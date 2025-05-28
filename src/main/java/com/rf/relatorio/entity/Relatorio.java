package com.rf.relatorio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Relatorio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String datadorelatorio;
	
	private String datadehoje;
	
	@Column(nullable = false)
	private String alteracao;
	
	@Column(nullable = false)
	private String nomeequipe;
	
	@Column(nullable = false)
	private String nomeinspetor;
	
	@Column(nullable = false)
	private String agentesdaequipe;
	
	@Column(nullable = false)
	private String agentesparapermultar;
	
	@Column(nullable = false)
	private String agentedefolgaparapermultar;
	
	@Column(nullable = false)
	private String agentesparareforco;
	
	@Column(nullable = false)
	private String agentesfaltoso;
	
	@Column(nullable = false)
	@Lob
	private String texto1;
	
	@Column(nullable = false)
	@Lob
	private String texto2;
	
	@Lob
	@Column(nullable = false)
	private String texto3;
	
}

