package br.com.southsystem.assembleia.model.pauta.dto;

import br.com.southsystem.assembleia.model.sessao.dto.SessaoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PautaIniciadaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private SessaoDTO sessao;
}