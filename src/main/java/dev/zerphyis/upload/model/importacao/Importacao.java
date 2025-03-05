package dev.zerphyis.upload.model.importacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_importacao")
public class Importacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime dataHoraImportacao;
    @NotNull
    private LocalDate dataTransacao;

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public LocalDateTime getDataHoraImportacao() {
        return dataHoraImportacao;
    }

    public void setDataHoraImportacao(LocalDateTime dataHoraImportacao) {
        this.dataHoraImportacao = dataHoraImportacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
