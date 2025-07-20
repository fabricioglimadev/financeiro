package br.dev.fabricioglima.financeiro.repository;

import br.dev.fabricioglima.financeiro.entity.CaixaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CaixaRepository extends JpaRepository<CaixaEntity, Long> {

  List<CaixaEntity> findByDataLancamentoBetween(LocalDate dataInicio, LocalDate dataFim);

  List<CaixaEntity> findByTipoLancamentoAndDataLancamentoBetween(String tipo, LocalDate inicio, LocalDate fim);

  @Query("SELECT SUM(c.valor) FROM CaixaEntity c WHERE c.tipoLancamento = :tipo AND c.dataLancamento BETWEEN :inicio AND :fim")
  BigDecimal somarPorTipoPorPeriodo(@Param("tipo") String tipo, @Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

}
