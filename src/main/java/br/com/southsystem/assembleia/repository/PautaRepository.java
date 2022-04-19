package br.com.southsystem.assembleia.repository;

import br.com.southsystem.assembleia.model.entities.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}