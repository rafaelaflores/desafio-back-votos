package br.com.southsystem.assembleia.service;

import br.com.southsystem.assembleia.exception.AssociadoException;
import br.com.southsystem.assembleia.exception.SessaoIniciadaException;
import br.com.southsystem.assembleia.model.associado.dto.AssociadoDTO;
import br.com.southsystem.assembleia.model.entities.Associado;
import br.com.southsystem.assembleia.model.entities.Pauta;
import br.com.southsystem.assembleia.model.entities.Voto;
import br.com.southsystem.assembleia.model.entities.voto.VotoPK;
import br.com.southsystem.assembleia.model.pauta.dto.PautaDTO;
import br.com.southsystem.assembleia.model.voto.dto.VotoDTO;
import br.com.southsystem.assembleia.model.voto.request.VotoRequest;
import br.com.southsystem.assembleia.repository.AssociadoRepository;
import br.com.southsystem.assembleia.repository.PautaRepository;
import br.com.southsystem.assembleia.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class VotoService {
    @Autowired
    VotoRepository votoRepository;

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    AssociadoRepository associadoRepository;

    public VotoDTO registrarVoto(VotoRequest votoRequest) {
        Optional<Associado> optionalAssociado = associadoRepository.findById(votoRequest.getAssociado_id());
        if(optionalAssociado.isEmpty()) {
            throw new IllegalArgumentException("O associado deve estar cadastrado para registrar o voto!");
        }

        Optional<Pauta> optionalPauta = pautaRepository.findById(votoRequest.getPauta_id());
        if(optionalPauta.isEmpty()) {
            throw new IllegalArgumentException("A pauta não foi cadastrada");
        }

        Pauta pauta = optionalPauta.get();
        if(Objects.isNull(pauta.getSessao())) {
            throw new SessaoIniciadaException("A sessão de votação em uma pauta deve ser iniciada antes de registrar um voto!");
        }
        if(pauta.getSessao().getFinalSessao().isBefore(LocalDateTime.now())) {
            throw new SessaoIniciadaException("O tempo final da sessão já foi excedido!");
        }

        VotoPK votoPK = new VotoPK(votoRequest.getPauta_id(), votoRequest.getAssociado_id());

        Optional<Voto> optionalVoto = votoRepository.findById(votoPK);
        if(optionalVoto.isPresent()) {
            throw new AssociadoException("O associado já registrou um voto nesta pauta");
        }

        Associado associado = optionalAssociado.get();

        Voto voto = new Voto();
        voto.setId(votoPK);
        voto.setVoto(votoRequest.isVoto());
        voto.setPauta(pauta);
        voto.setAssociado(associado);

        Voto votoRegistrado = votoRepository.save(voto);

        PautaDTO pautaDTO = new PautaDTO(pauta.getId(), pauta.getTitulo(), pauta.getDescricao());
        AssociadoDTO associadoDTO = new AssociadoDTO(associado.getId(), associado.getNome(), associado.getCpf());

        VotoDTO votoDTO = new VotoDTO();
        votoDTO.setVoto(votoRegistrado.isVoto());
        votoDTO.setId_pauta(pautaDTO.getId());
        votoDTO.setId_associado(associadoDTO.getId());
    }
}
