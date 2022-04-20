package br.com.southsystem.assembleia.repository;

import br.com.southsystem.assembleia.model.entities.Voto;
import br.com.southsystem.assembleia.model.entities.voto.VotoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, VotoPK> {
}
