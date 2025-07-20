package br.dev.fabricioglima.financeiro.repository;

import br.dev.fabricioglima.financeiro.entity.CaixaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CaixaRepository extends JpaRepository<CaixaEntity, Long> {

  List<CaixaEntity> findByDataLancamentoBetween(LocalDate dataInicio, LocalDate dataFim);

  List<CaixaEntity> findByTipoLancamentoAndDataLancamentoBetween(String tipo, LocalDate inicio, LocalDate fim);


}
