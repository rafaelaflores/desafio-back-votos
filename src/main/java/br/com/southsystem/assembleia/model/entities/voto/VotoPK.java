package br.com.southsystem.assembleia.model.entities.voto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class VotoPK implements Serializable {
    private Long id_pauta;
    private Long id_associado;
}