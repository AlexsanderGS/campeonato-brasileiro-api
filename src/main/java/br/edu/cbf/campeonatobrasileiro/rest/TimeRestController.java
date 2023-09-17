package br.edu.cbf.campeonatobrasileiro.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.cbf.campeonatobrasileiro.dto.TimeDTO;
import br.edu.cbf.campeonatobrasileiro.service.TimeService;

@RestController
@RequestMapping(value = "/times")
public class TimeRestController {
	
	@Autowired
	private TimeService timeService;
	
	@GetMapping
	public ResponseEntity<List<TimeDTO>> getTimes() {
		return ResponseEntity.ok().body(timeService.listarTimes());
		
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<TimeDTO> getTime(@PathVariable Integer id) {
		return ResponseEntity.ok().body(timeService.obterTimes(id));
		
	}
	
	@PostMapping
	public ResponseEntity<TimeDTO> cadastrarTime(@RequestBody TimeDTO time) throws Exception {
		return ResponseEntity.ok().body(timeService.cadastrarTime(time));
	}

}
