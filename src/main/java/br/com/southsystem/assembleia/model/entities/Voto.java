package br.com.southsystem.assembleia.model.entities;

import javax.persistence.*;
import br.com.southsystem.assembleia.model.entities.voto.VotoPK;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Voto {
    @EmbeddedId
    private VotoPK id;

    private boolean voto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "associado_id")
    private Associado associado;
}

