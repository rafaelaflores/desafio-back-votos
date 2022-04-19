package br.com.southsystem.assembleia.model.associado.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssociadoDTO {
    private Long id;
    private String nome;
    private String cpf;
}