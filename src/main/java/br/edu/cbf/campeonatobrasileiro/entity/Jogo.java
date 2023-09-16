package br.edu.cbf.campeonatobrasileiro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Jogo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Time time1;
	private Time time2;
	private Integer golsTime1;
	private Integer golsTime2;
	private Integer publicoPagante;
	private String uuid;

}
