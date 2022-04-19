package br.com.southsystem.assembleia.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Pauta {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String titulo;

    @Column(nullable = false, length = 150)
    private String descricao;

    @OneToOne(mappedBy = "pauta")
    private Sessao sessao;

    @OneToMany(mappedBy = "pauta")
    private List<Voto> voto;
}