package br.com.southsystem.assembleia.model.sessao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessaoDTO {
    private Long id;
    private LocalDateTime inicio;
    private LocalDateTime finalSessao;
}
