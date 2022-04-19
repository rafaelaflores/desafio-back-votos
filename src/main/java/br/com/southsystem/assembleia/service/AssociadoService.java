package br.com.southsystem.assembleia.service;

import br.com.southsystem.assembleia.model.associado.dto.AssociadoDTO;
import br.com.southsystem.assembleia.model.associado.request.AssociadoRequest;
import br.com.southsystem.assembleia.model.entities.Associado;
import br.com.southsystem.assembleia.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {
    @Autowired
    private AssociadoRepository associadoRepository;

    public AssociadoDTO cadastrarAssociado(AssociadoRequest associadoRequest) {
        Associado associado = new Associado();

        associado.setNome(associadoRequest.getNome());
        associado.setCpf(associadoRequest.getCpf());

        Associado associadoRegistrado = associadoRepository.save(associado);

        AssociadoDTO associadoDTO = new AssociadoDTO();

        associadoDTO.setId(associadoRegistrado.getId());
        associadoDTO.setNome(associadoRegistrado.getNome());
        associadoDTO.setCpf(associadoRegistrado.getCpf());

        return associadoDTO;
    }
}