package br.com.southsystem.assembleia.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
}