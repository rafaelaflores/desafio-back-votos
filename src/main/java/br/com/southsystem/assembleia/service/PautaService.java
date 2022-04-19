package br.com.southsystem.assembleia.service;

import br.com.southsystem.assembleia.model.entities.Pauta;
import br.com.southsystem.assembleia.model.pauta.dto.PautaDTO;
import br.com.southsystem.assembleia.model.pauta.request.PautaRequest;
import br.com.southsystem.assembleia.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {
    @Autowired
    PautaRepository pautaRepository;

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
}
