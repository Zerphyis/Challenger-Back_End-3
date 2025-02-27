package dev.Zerphyis.upload.repository;

import dev.Zerphyis.upload.Entity.importacao.Importacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RepositoryImportacao extends JpaRepository<Importacao,Long> {
    boolean existsByDataTransacao(LocalDate dataTransacao);
    Optional<Importacao> findByDataTransacao(LocalDate dataTransacao)
}
