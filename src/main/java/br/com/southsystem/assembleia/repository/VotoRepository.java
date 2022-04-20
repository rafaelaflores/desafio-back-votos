package br.com.southsystem.assembleia.repository;

import br.com.southsystem.assembleia.model.entities.Pauta;
import br.com.southsystem.assembleia.model.entities.Voto;
import br.com.southsystem.assembleia.model.entities.voto.VotoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, VotoPK> {
    List<Voto> findVotoByPauta(Pauta pauta);
}