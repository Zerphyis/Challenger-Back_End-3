package dev.Zerphyis.upload.repository;

import dev.Zerphyis.upload.Entity.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryTransacao extends JpaRepository<Transacao,Long> {
}
