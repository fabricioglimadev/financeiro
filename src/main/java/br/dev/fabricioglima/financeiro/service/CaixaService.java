package br.dev.fabricioglima.financeiro.service;

import br.dev.fabricioglima.financeiro.CaixaRepository;
import br.dev.fabricioglima.financeiro.dtos.CaixaRequestDto;
import br.dev.fabricioglima.financeiro.dtos.CaixaResponseDto;
import br.dev.fabricioglima.financeiro.entity.CaixaEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CaixaService {

  private final CaixaRepository repository;

  public CaixaService(CaixaRepository repository) {
    this.repository = repository;
  }


  public CaixaResponseDto insert(CaixaRequestDto request) {

    CaixaEntity caixaEntity = new CaixaEntity();
    caixaEntity.setTipoLancamento(request.getTipoLancamento());
    caixaEntity.setDataLancamento(LocalDate.now());
    if (request.getDataLancamento() != null) {
      caixaEntity.setDataLancamento(request.getDataLancamento());
    }
    caixaEntity.setDescricao(request.getDescricao());
    caixaEntity.setValor(request.getValor());

    caixaEntity = repository.save(caixaEntity);

    CaixaResponseDto response = new CaixaResponseDto();
    response.setTipoLancamento(caixaEntity.getTipoLancamento());
    response.setDataLancamento(caixaEntity.getDataLancamento());
    response.setDescricao(caixaEntity.getDescricao());
    response.setValor(caixaEntity.getValor());
    response.setId(caixaEntity.getId());

    return response;
  }

  public CaixaResponseDto update(Long id, CaixaRequestDto request) {

    Optional<CaixaEntity> caixaOptional = repository.findById(id);
    if(caixaOptional.isEmpty()){
      throw new RuntimeException("Lançamento não encontrado");
    }

    CaixaEntity caixaEntity = caixaOptional.get();
    caixaEntity.setTipoLancamento(request.getTipoLancamento());
    caixaEntity.setDataLancamento(LocalDate.now());
    if (request.getDataLancamento() != null) {
      caixaEntity.setDataLancamento(request.getDataLancamento());
    }
    caixaEntity.setDescricao(request.getDescricao());
    caixaEntity.setValor(request.getValor());

    caixaEntity = repository.save(caixaEntity);

    CaixaResponseDto response = new CaixaResponseDto();
    response.setTipoLancamento(caixaEntity.getTipoLancamento());
    response.setDataLancamento(caixaEntity.getDataLancamento());
    response.setDescricao(caixaEntity.getDescricao());
    response.setValor(caixaEntity.getValor());
    response.setId(caixaEntity.getId());

    return response;
  }


}
