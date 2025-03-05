package dev.zerphyis.upload.repository;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.zerphyis.upload.model.importacao.Importacao;

@Repository
public interface ImportacaoRepository extends JpaRepository<Importacao, Long> {
    boolean existsByDataTransacao(LocalDate dataTransacao);

    Optional<Importacao> findByDataTransacao(LocalDate dataTransacao);
}
