package br.com.alura.leilao.util;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LeilaoBuilder {

    private String nome;
    private BigDecimal valorInicial;
    private Usuario usuario;
    private LocalDate dataAbertura;

    public LeilaoBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public LeilaoBuilder comValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
        return this;
    }

    public LeilaoBuilder comData(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
        return this;
    }

    public LeilaoBuilder comUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Leilao build() {
        return new Leilao(
                nome,
                valorInicial,
                dataAbertura,
                usuario
        );
    }
}
