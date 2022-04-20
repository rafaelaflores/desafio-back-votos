package br.com.southsystem.assembleia.model.pauta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaResultadoDTO {
    private Boolean pautaAprovada;
    private Integer simVotos = 0;
    private Integer naoVotos = 0;
    private Integer totalVotos;

    public Boolean isPautaAprovada() {
        return this.simVotos > this.naoVotos;
    }
}