package br.edu.cbf.campeonatobrasileiro.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.cbf.campeonatobrasileiro.entity.Time;
import br.edu.cbf.campeonatobrasileiro.service.TimeService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/times")
public class TimeRestController {
	
	@Autowired
	private TimeService timeService;
	
	@ApiOperation(value  = "Lista os times")
	@GetMapping
	public ResponseEntity<List<Time>> getTimes() {
		return ResponseEntity.ok().body(timeService.listarTimes());
		
	}
	
	@ApiOperation(value  = "Obtém os dados de um time")
	@GetMapping(value = "{id}")
	public ResponseEntity<Time> getTime(@PathVariable Integer id) {
		return ResponseEntity.ok().body(timeService.obterTimes(id));
		
	}
	
	@ApiOperation(value  = "Cadastra um time")
	@PostMapping
	public ResponseEntity<Void> cadastrarTime(Time time){
		timeService.cadastrarTime(time);
		return ResponseEntity.ok().build();
	}

}
