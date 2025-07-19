package br.dev.fabricioglima.financeiro.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "caixa")
public class CaixaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "TIPO_LANCAMENTO")
  private String tipoLancamento; //Entrada ou Saída

  @Column(name = "DATA_LANCAMENTO")
  private LocalDate dataLancamento;

  private String descricao;

  private BigDecimal valor;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTipoLancamento() {
    return tipoLancamento;
  }

  public void setTipoLancamento(String tipoLancamento) {
    this.tipoLancamento = tipoLancamento;
  }

  public LocalDate getDataLancamento() {
    return dataLancamento;
  }

  public void setDataLancamento(LocalDate dataLancamento) {
    this.dataLancamento = dataLancamento;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }
}
