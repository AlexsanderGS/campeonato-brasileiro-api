package br.edu.cbf.campeonatobrasileiro.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.cbf.campeonatobrasileiro.dto.JogoDTO;
import br.edu.cbf.campeonatobrasileiro.entity.Jogo;
import br.edu.cbf.campeonatobrasileiro.entity.Time;
import br.edu.cbf.campeonatobrasileiro.repository.JogoRepository;

@Service
public class JogoService {
	
	@Autowired
	JogoRepository jogoRepository;
	
	@Autowired
	TimeService timeService;
	
	public void gerarJogos(LocalDateTime primeiraRodada) {
        final List<Time> times = timeService.findAll();
        List<Time> all1 = new ArrayList<>();
        List<Time> all2 = new ArrayList<>();
        all1.addAll(times);//.subList(0, times.size()/2));
        all2.addAll(times);//.subList(all1.size(), times.size()));

        jogoRepository.deleteAll();

        List<Jogo> jogos = new ArrayList<>();

        int t = times.size();
        int m = times.size() / 2;
        LocalDateTime dataJogo = primeiraRodada;
        Integer rodada = 0;
        for (int i = 0; i < t - 1; i++) {
            rodada = i + 1;
            for (int j = 0; j < m; j++) {
                //Teste para ajustar o mando de campo
                Time time1;
                Time time2;
                if (j % 2 == 1 || i % 2 == 1 && j == 0) {
                    time1 = times.get(t - j - 1);
                    time2 = times.get(j);
                } else {
                    time1 = times.get(j);
                    time2 = times.get(t - j - 1);
                }
                if (time1 == null) {
                    System.out.println("Time  1 null");
                }
                jogos.add(gerarJogo(dataJogo, rodada, time1, time2));
                dataJogo = dataJogo.plusDays(7);
            }
            //Gira os times no sentido horário, mantendo o primeiro no lugar
            times.add(1, times.remove(times.size() - 1));
        }

        jogos.forEach(jogo -> System.out.println(jogo));

        jogoRepository.saveAll(jogos);

        List<Jogo> jogos2 = new ArrayList<>();

        jogos.forEach(jogo -> {
            Time time1 = jogo.getTime2();
            Time time2 = jogo.getTime1();
            jogos2.add(gerarJogo(jogo.getData().plusDays(7 * jogos.size()), jogo.getRodada() + jogos.size(), time1, time2));
        });
        jogoRepository.saveAll(jogos2);
    }

	private Jogo gerarJogo(LocalDateTime dataJogo, Integer rodada, Time time1, Time time2) {
		Jogo jogo = new Jogo();
        jogo.setTime1(time1);
        jogo.setTime2(time2);
        jogo.setRodada(rodada);
        jogo.setData(dataJogo);
        jogo.setEncerrado(false);
        jogo.setGolsTime1(0);
        jogo.setGolsTime2(0);
        jogo.setPublicoPagante(0);
        return jogo;
	}
	
	private JogoDTO toDto(Jogo entity) {
		JogoDTO dto = new JogoDTO();
		dto.setId(entity.getId());
		dto.setData(entity.getData());
		dto.setEncerrado(entity.getEncerrado());
		dto.setGolsTime1(entity.getGolsTime1());
		dto.setGolsTime2(entity.getGolsTime1());
		dto.setPublicoPagante(entity.getPublicoPagante());
		dto.setRodada(entity.getRodada());
		dto.setTime1(timeService.toDto(entity.getTime1()));
		dto.setTime2(timeService.toDto(entity.getTime2()));
		return dto;
	}

	public List<JogoDTO> listarJogo() {
		return jogoRepository.findAll().stream().map(entity -> toDto(entity)).collect(Collectors.toList());
	}

	public JogoDTO finalizar(Integer id, JogoDTO jogoDto) throws Exception {
		Optional<Jogo> optionalJogo = jogoRepository.findById(id);
		if (optionalJogo.isPresent()) {
			final Jogo jogo = optionalJogo.get();
			jogo.setGolsTime1(jogoDto.getGolsTime1());
			jogo.setGolsTime2(jogoDto.getGolsTime2());
			jogo.setEncerrado(true);
			jogo.setPublicoPagante(jogoDto.getPublicoPagante());
			return toDto(jogoRepository.save(jogo));
		} else {
			throw new Exception("Jogo não existe.");
		}
	}

	/*public Object obterClassificacao() {
		// TODO Auto-generated method stub
		return null;
	}*/

	public JogoDTO obterJogo(Integer id) {
		return toDto(jogoRepository.findById(id).get());
	}

}
