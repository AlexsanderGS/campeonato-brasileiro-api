package br.edu.cbf.campeonatobrasileiro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Time {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 20)
	private String nome;
	@Column(length = 4)
	private String sigla;
	@Column(length = 2, name = "estado")
	private String uf;
	private String estadio;

}
