package br.edu.cbf.campeonatobrasileiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.cbf.campeonatobrasileiro.entity.Time;
import br.edu.cbf.campeonatobrasileiro.repository.TimeRepository;

public class TimeService {
	
	@Autowired
	private TimeRepository repository;
	
	public void cadastrarTime(Time time) {
		repository.save(time);
		
	}
	
	public List<Time> listarTimes(){
		return repository.findAll();
	}
	
	public Time obterTimes(Integer id){
		return repository.findById(id).get();
	}


}
