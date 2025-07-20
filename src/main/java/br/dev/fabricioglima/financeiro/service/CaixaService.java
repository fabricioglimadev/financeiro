package br.dev.fabricioglima.financeiro.service;

import br.dev.fabricioglima.financeiro.enums.TipoLancamento;
import br.dev.fabricioglima.financeiro.repository.CaixaRepository;
import br.dev.fabricioglima.financeiro.dtos.CaixaRequestDto;
import br.dev.fabricioglima.financeiro.dtos.CaixaResponseDto;
import br.dev.fabricioglima.financeiro.entity.CaixaEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CaixaService {

  private final CaixaRepository repository;

  public CaixaService(CaixaRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public CaixaResponseDto insert(CaixaRequestDto request) {

    CaixaEntity caixaEntity = new CaixaEntity();

    caixaEntity.setTipoLancamento("S");
    if(request.getTipoLancamento() == TipoLancamento.ENTRADA){
      caixaEntity.setTipoLancamento("E");
    }

    caixaEntity.setDataLancamento(LocalDate.now());
    if (request.getDataLancamento() != null) {
      caixaEntity.setDataLancamento(request.getDataLancamento());
    }
    caixaEntity.setDescricao(request.getDescricao());
    caixaEntity.setValor(request.getValor());

    caixaEntity = repository.save(caixaEntity);

    CaixaResponseDto response = new CaixaResponseDto();

    response.setTipoLancamento(TipoLancamento.valueOf(caixaEntity.getTipoLancamento().equals("E") ? "ENTRADA" : "SAIDA"));

    response.setDataLancamento(caixaEntity.getDataLancamento());
    response.setDescricao(caixaEntity.getDescricao());
    response.setValor(caixaEntity.getValor());
    response.setId(caixaEntity.getId());

    return response;
  }

  @Transactional
  public CaixaResponseDto update(Long id, CaixaRequestDto request) {

    Optional<CaixaEntity> caixaOptional = repository.findById(id);
    if (caixaOptional.isEmpty()) {
      throw new RuntimeException("Lançamento não encontrado");
    }

    CaixaEntity caixaEntity = caixaOptional.get();


    caixaEntity.setTipoLancamento("S");
    if(request.getTipoLancamento() == TipoLancamento.ENTRADA){
      caixaEntity.setTipoLancamento("E");
    }



    caixaEntity.setDataLancamento(LocalDate.now());
    if (request.getDataLancamento() != null) {
      caixaEntity.setDataLancamento(request.getDataLancamento());
    }
    caixaEntity.setDescricao(request.getDescricao());
    caixaEntity.setValor(request.getValor());

    caixaEntity = repository.save(caixaEntity);

    CaixaResponseDto response = new CaixaResponseDto();
    response.setTipoLancamento(TipoLancamento.valueOf(caixaEntity.getTipoLancamento().equals("E") ? "ENTRADA" : "SAIDA"));
    response.setDataLancamento(caixaEntity.getDataLancamento());
    response.setDescricao(caixaEntity.getDescricao());
    response.setValor(caixaEntity.getValor());
    response.setId(caixaEntity.getId());

    return response;
  }

  @Transactional
  public void delete(Long id) {
    Optional<CaixaEntity> caixaOptional = repository.findById(id);
    if (caixaOptional.isEmpty()) {
      throw new RuntimeException("Lançamento não encontrado");
    }

    repository.deleteById(id);
  }

  public CaixaResponseDto findById(Long id) {

    Optional<CaixaEntity> caixaOptional = repository.findById(id);
    if (caixaOptional.isEmpty()) {
      throw new RuntimeException("Lançamento não encontrado");
    }

    CaixaEntity caixaEntity = caixaOptional.get();

    CaixaResponseDto response = new CaixaResponseDto();

    response.setTipoLancamento(TipoLancamento.valueOf(caixaEntity.getTipoLancamento().equals("E") ? "ENTRADA" : "SAIDA"));

    response.setDataLancamento(caixaEntity.getDataLancamento());
    response.setDescricao(caixaEntity.getDescricao());
    response.setValor(caixaEntity.getValor());
    response.setId(caixaEntity.getId());

    return response;

  }

  public List<CaixaResponseDto> findByDataLancamentoBetween(LocalDate dataInicial, LocalDate dataFinal){

    List<CaixaResponseDto> list = new ArrayList<>();

    List<CaixaEntity> entities = repository.findByDataLancamentoBetween(dataInicial, dataFinal);

    entities.forEach(e -> {
      CaixaResponseDto response = new CaixaResponseDto();
      response.setId(e.getId());
      response.setTipoLancamento(TipoLancamento.valueOf(e.getTipoLancamento().equals("E") ? "ENTRADA" : "SAIDA"));
      response.setDataLancamento(e.getDataLancamento());
      response.setDescricao(e.getDescricao());
      response.setValor(e.getValor());
      list.add(response);
    });

    return list;

  }

  public List<CaixaResponseDto> findByTipoLancamentoAndDataLancamentoBetween(String tipoLancamento, LocalDate dataInicial, LocalDate dataFinal){

    List<CaixaResponseDto> list = new ArrayList<>();

    List<CaixaEntity> entities = repository.findByTipoLancamentoAndDataLancamentoBetween(tipoLancamento, dataInicial, dataFinal);

    entities.forEach(e -> {
      CaixaResponseDto response = new CaixaResponseDto();
      response.setId(e.getId());
      response.setTipoLancamento(TipoLancamento.valueOf(e.getTipoLancamento().equals("E") ? "ENTRADA" : "SAIDA"));
      response.setDataLancamento(e.getDataLancamento());
      response.setDescricao(e.getDescricao());
      response.setValor(e.getValor());
      list.add(response);
    });

    return list;

  }

  public BigDecimal somaDasEntradas(LocalDate dataInicial, LocalDate dataFinal){
    BigDecimal response = repository.somarPorTipoPorPeriodo("E", dataInicial, dataFinal);
    if(response == null){
      return BigDecimal.ZERO;
    }
    return response;
  }

  public BigDecimal somaDasSaidas(LocalDate dataInicial, LocalDate dataFinal){
    BigDecimal response = repository.somarPorTipoPorPeriodo("S", dataInicial, dataFinal);
    if(response == null){
      return BigDecimal.ZERO;
    }
    return response;
  }




}
