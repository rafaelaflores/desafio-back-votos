package br.com.southsystem.assembleia.model.associado.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class AssociadoRequest {
    @NotEmpty
    private String cpf;
    @NotEmpty
    private String nome;
}