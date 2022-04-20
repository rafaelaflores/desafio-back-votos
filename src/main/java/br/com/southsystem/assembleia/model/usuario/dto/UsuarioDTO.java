package br.com.southsystem.assembleia.model.usuario.dto;

import br.com.southsystem.assembleia.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private StatusEnum status;
}
