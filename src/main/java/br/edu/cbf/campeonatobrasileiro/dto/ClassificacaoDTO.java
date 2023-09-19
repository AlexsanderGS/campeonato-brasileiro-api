package br.edu.cbf.campeonatobrasileiro.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ClassificacaoDTO {
	
	private List<ClassificacaoTimeDTO> times = new ArrayList<>();

	public List<ClassificacaoTimeDTO> getTimes() {
		return times;
	}

	public void setTimes(List<ClassificacaoTimeDTO> times) {
		this.times = times;
	}
	
	

}
