package dev.zerphyis.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.zerphyis.upload.model.transacao.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
