package br.edu.cbf.campeonatobrasileiro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.cbf.campeonatobrasileiro.dto.TimeDTO;
import br.edu.cbf.campeonatobrasileiro.entity.Time;
import br.edu.cbf.campeonatobrasileiro.repository.TimeRepository;

@Service
public class TimeService {

	@Autowired
	private TimeRepository repository;

	public TimeDTO cadastrarTime(TimeDTO time) throws Exception {
		Time entity = toEntity(time);
		if(time.getId() == null) {
			entity = repository.save(entity);
			return toDto(entity);
		}else {
			throw new Exception("Time já cadastrado");
		}

	}

	private Time toEntity(TimeDTO time) {
		Time entity = new Time();
		entity.setEstadio(time.getEstadio());
		entity.setSigla(time.getSigla());
		entity.setNome(time.getNome());
		entity.setUf(time.getUf());
		return entity;
	}

	private TimeDTO toDto(Time entity) {
		TimeDTO dto = new TimeDTO();
		dto.setEstadio(entity.getEstadio());
		dto.setSigla(entity.getSigla());
		dto.setNome(entity.getNome());
		dto.setUf(entity.getUf());
		return dto;
	}

	public List<TimeDTO> listarTimes() {
		return repository.findAll().stream().map(entity -> toDto(entity)).collect(Collectors.toList());
	}

	public TimeDTO obterTimes(Integer id) {
		return toDto(repository.findById(id).get());
	}

}
