package br.com.southsystem.assembleia.service;

import br.com.southsystem.assembleia.exception.SessaoIniciadaException;
import br.com.southsystem.assembleia.model.entities.Pauta;
import br.com.southsystem.assembleia.model.sessao.dto.SessaoDTO;
import br.com.southsystem.assembleia.model.sessao.request.SessaoRequest;
import br.com.southsystem.assembleia.repository.PautaRepository;
import br.com.southsystem.assembleia.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SessaoService {
    @Autowired
    PautaService pautaService;

    @Autowired
    SessaoRepository sessaoRepository;

    @Autowired
    PautaRepository pautaRepository;

    public SessaoDTO criarSessao(Long id, SessaoRequest sessaoRequest) {
        //verificando se o usuario digitou um valor maior que zero para duracao da sessao de votacao
        if(sessaoRequest.getDuracao() != null && sessaoRequest.getDuracao() < 0) {
            throw new IllegalArgumentException("A duração da sessão deve ser maior que zero");
        }

        //caso o usuario nao digite uma duracao ou coloque 0, setamos um valor default igual a 1 minuto
        if(sessaoRequest.getDuracao() == null || sessaoRequest.getDuracao() == 0) {
            sessaoRequest.setDuracao(1);
        }

        //garantir que o id tem uma pauta correspondente
        Optional<Pauta> optionalPauta = pautaRepository.findById(id);
        if(optionalPauta.isEmpty()) {
            throw new IllegalArgumentException("Pauta não encontrada");
        }

        //se tiver, resgatamos os dados da pauta em questao
        Pauta pauta;
        pauta = optionalPauta.get();

        if(Objects.nonNull(pauta.getSessao())) {
            //se a sessão nao for nula, entao já foi registrada uma sessão nessa pauta
            throw new SessaoIniciadaException("A sessão de votação já foi iniciada nesta pauta");
        }

        return null;
    }
}
