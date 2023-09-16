package br.edu.cbf.campeonatobrasileiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.cbf.campeonatobrasileiro.entity.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Integer> {

}
