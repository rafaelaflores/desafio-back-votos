package br.com.southsystem.assembleia.model.pauta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PautaDTO {
    private Long id;
    private String titulo;
    private String descricao;
}
