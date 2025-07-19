package br.dev.fabricioglima.financeiro.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CaixaResponseDto {

  private Long id;
  private String tipoLancamento;
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
