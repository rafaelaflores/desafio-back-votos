package br.com.southsystem.assembleia.model.voto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotoDTO {
    private boolean voto;
    private Long id_pauta;
    private Long id_associado;
}