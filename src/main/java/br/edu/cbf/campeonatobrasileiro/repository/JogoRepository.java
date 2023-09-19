package br.edu.cbf.campeonatobrasileiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.cbf.campeonatobrasileiro.entity.Jogo;
import br.edu.cbf.campeonatobrasileiro.entity.Time;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {
	
	// SELECT * FROM JOGO WHERE TIME1 = :TIME1 AND ENCERRADO = :ENCERRADO
	List<Jogo> findByTime1AndEncerrado(Time time1, Boolean encerrado);
	List<Jogo> findByTime2AndEncerrado(Time time1, Boolean encerrado);
	List<Jogo> findBydEncerrado(Boolean encerrado);

}
