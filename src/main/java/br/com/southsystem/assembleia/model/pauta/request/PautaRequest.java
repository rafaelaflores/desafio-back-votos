package br.com.southsystem.assembleia.model.pauta.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PautaRequest {
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
}
