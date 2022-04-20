package br.com.southsystem.assembleia.service;

import br.com.southsystem.assembleia.exception.PautaException;
import br.com.southsystem.assembleia.model.entities.Pauta;
import br.com.southsystem.assembleia.model.entities.Sessao;
import br.com.southsystem.assembleia.model.entities.Voto;
import br.com.southsystem.assembleia.model.pauta.dto.PautaDTO;
import br.com.southsystem.assembleia.model.pauta.dto.PautaIniciadaDTO;
import br.com.southsystem.assembleia.model.pauta.dto.PautaResultadoDTO;
import br.com.southsystem.assembleia.model.pauta.request.PautaRequest;
import br.com.southsystem.assembleia.model.sessao.dto.SessaoDTO;
import br.com.southsystem.assembleia.repository.PautaRepository;
import br.com.southsystem.assembleia.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PautaService {
    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    VotoRepository votoRepository;

    public PautaDTO cadastrarPauta(PautaRequest pautaRequest) {
        Pauta pauta = new Pauta();

        pauta.setTitulo(pautaRequest.getTitulo());
        pauta.setDescricao(pautaRequest.getDescricao());

        Pauta pautaRegistrada = pautaRepository.save(pauta);

        PautaDTO pautaDTO = new PautaDTO();

        pautaDTO.setId(pautaRegistrada.getId());
        pautaDTO.setTitulo(pautaRegistrada.getTitulo());
        pautaDTO.setDescricao(pautaRegistrada.getDescricao());

        return pautaDTO;
    }

    //esse método foi criado para estruturar o objeto de pauta, caso haja ou não uma sessão aberta
    private PautaDTO getPautaDTO(Pauta pauta) {
        PautaDTO pautaDTO;
        if (Objects.nonNull(pauta.getSessao())) {
            Sessao sessao = pauta.getSessao();
            SessaoDTO sessaoDTO = new SessaoDTO();
            PautaIniciadaDTO pautaIniciadaDTO = new PautaIniciadaDTO();

            pautaIniciadaDTO.setId(pauta.getId());
            pautaIniciadaDTO.setTitulo(pauta.getTitulo());
            pautaIniciadaDTO.setDescricao(pauta.getDescricao());
            pautaIniciadaDTO.setSessao(sessaoDTO);

            sessaoDTO.setId(sessao.getId());
            sessaoDTO.setInicio(sessao.getInicioSessao());
            sessaoDTO.setFinalSessao(sessao.getFinalSessao());
            pautaDTO = new PautaDTO(pautaIniciadaDTO.getId(), pautaIniciadaDTO.getTitulo(), pautaIniciadaDTO.getDescricao(), pautaIniciadaDTO.getSessao());
        } else {
            pautaDTO = new PautaDTO(pauta.getId(), pauta.getTitulo(), pauta.getDescricao());
        }
        return pautaDTO;
    }

    public List<PautaDTO> listarPautas() {
        List<Pauta> pautasRegistradas = pautaRepository.findAll();
        return pautasRegistradas.stream().map(this::getPautaDTO).collect(Collectors.toList());
    }

    public Optional<PautaDTO> buscarPauta(Long id) {
        Optional<Pauta> optionalPauta = pautaRepository.findById(id);
        Optional<PautaDTO> optionalPautaDTO;
        if (optionalPauta.isPresent()) {
            Pauta pauta = optionalPauta.get();
            PautaDTO pautaDTO = getPautaDTO(pauta);
            optionalPautaDTO = Optional.of(pautaDTO);
        } else {
            optionalPautaDTO = Optional.empty();
        }
        return optionalPautaDTO;
    }

    public PautaResultadoDTO getResultado(Long id) {
        Pauta pauta = new Pauta();
        pauta.setId(id);

        List<Voto> votosRegistrados = votoRepository.findVotoByPauta(pauta);

        if(votosRegistrados.isEmpty()) {
            throw new PautaException("Não há votos para a pauta em questão");
        }

        PautaResultadoDTO pautaResultadoDTO = new PautaResultadoDTO();

        for(Voto voto: votosRegistrados) {
            if(voto.isVoto()) {
                pautaResultadoDTO.setSimVotos(pautaResultadoDTO.getSimVotos() + 1);
            } else {
                pautaResultadoDTO.setNaoVotos(pautaResultadoDTO.getNaoVotos() + 1);
            }
        }

        pautaResultadoDTO.setTotalVotos(votosRegistrados.size());
        pautaResultadoDTO.setPautaAprovada(pautaResultadoDTO.isPautaAprovada());

        return pautaResultadoDTO;
    }
}
