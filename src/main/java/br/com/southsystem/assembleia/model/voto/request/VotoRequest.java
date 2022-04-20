package br.com.southsystem.assembleia.model.voto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoRequest {
    private boolean voto;
    private Long pauta_id;
    private Long associado_id;
}