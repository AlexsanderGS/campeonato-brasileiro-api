package br.edu.cbf.campeonatobrasileiro.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.cbf.campeonatobrasileiro.dto.ClassificacaoDTO;
import br.edu.cbf.campeonatobrasileiro.dto.JogoDTO;
import br.edu.cbf.campeonatobrasileiro.dto.JogoFinalizadoDTO;
import br.edu.cbf.campeonatobrasileiro.service.JogoService;

@RestController
@RequestMapping(value = "/jogos")
public class JogoRestController {
	
	@Autowired
	private JogoService jogoService;
	
	@PostMapping(value = "/gerar-jogos")
	public ResponseEntity<Void> gerarJogos(){
		jogoService.gerarJogos(LocalDateTime.now());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<JogoDTO>> obterJogos(){
		return ResponseEntity.ok().body(jogoService.listarJogo());
	}
	
	@PostMapping(value  = "/finalizar/{id}")
	public ResponseEntity<JogoDTO> finalizar(@PathVariable Integer id, @RequestBody JogoFinalizadoDTO jogoDto) throws Exception {
		return ResponseEntity.ok().body(jogoService.finalizar(id, jogoDto));
	}
	
	
	@GetMapping(value = "/classificacao")
	public ResponseEntity<ClassificacaoDTO> classificacao(@PathVariable Integer id) {
		return ResponseEntity.ok().body(jogoService.obterClassificacao());
	}
	
	@GetMapping(value = "/jogo/{id}")
	public ResponseEntity<JogoDTO> obterJogo(@PathVariable Integer id) {
		return ResponseEntity.ok().body(jogoService.obterJogo(id));
	}

}
