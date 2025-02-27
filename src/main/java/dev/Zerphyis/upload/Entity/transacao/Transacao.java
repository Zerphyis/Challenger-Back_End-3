package dev.Zerphyis.upload.Entity.transacao;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String bancoOrigem;
    @NotBlank

    private  String agenciaOrigem;
    @NotBlank

    private  String contaOrigem;
    @NotBlank

    private String bancoDestino;
    @NotBlank

    private String contaDestino;
    @NotBlank

    private String agenciaDestino;
    @NotNull
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que 0")
    private BigDecimal valor;
    @NotNull
    private LocalDateTime datatTransacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBancoOrigem() {
        return bancoOrigem;
    }

    public void setBancoOrigem(String bancoOrigem) {
        this.bancoOrigem = bancoOrigem;
    }

    public String getAgenciaOrigem() {
        return agenciaOrigem;
    }

    public void setAgenciaOrigem(String agenciaOrigem) {
        this.agenciaOrigem = agenciaOrigem;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getBancoDestino() {
        return bancoDestino;
    }

    public void setBancoDestino(String bancoDestino) {
        this.bancoDestino = bancoDestino;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public String getAgenciaDestino() {
        return agenciaDestino;
    }

    public void setAgenciaDestino(String agenciaDestino) {
        this.agenciaDestino = agenciaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDatatTransacao() {
        return datatTransacao;
    }

    public void setDatatTransacao(LocalDateTime datatTransacao) {
        this.datatTransacao = datatTransacao;
    }
}
