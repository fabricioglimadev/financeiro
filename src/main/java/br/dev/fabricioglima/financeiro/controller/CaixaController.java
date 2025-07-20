package br.dev.fabricioglima.financeiro.controller;

import br.dev.fabricioglima.financeiro.dtos.CaixaRequestDto;
import br.dev.fabricioglima.financeiro.dtos.CaixaResponseDto;
import br.dev.fabricioglima.financeiro.service.CaixaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

  private final CaixaService service;

  public CaixaController(CaixaService service) {
    this.service = service;
  }


  @GetMapping("/healthcheck")
  public String healthCheck(){
    return "API está no ar";
  }

  @PostMapping
  public ResponseEntity<?> insert(@RequestBody CaixaRequestDto request){

    try{
      CaixaResponseDto response = service.insert(request);
      return ResponseEntity.ok(response);
    } catch (Exception e){
      return ResponseEntity.internalServerError().build();
    }

  }

  @PutMapping("/{id}")
  public ResponseEntity<?> insert(@PathVariable Long id, @RequestBody CaixaRequestDto request){

    try{
      CaixaResponseDto response = service.update(id, request);
      return ResponseEntity.ok(response);
    } catch (RuntimeException e){
      return ResponseEntity.notFound().build();
    } catch (Exception e){
      return ResponseEntity.internalServerError().build();
    }

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> insert(@PathVariable Long id){

    try{
      service.delete(id);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException e){
      return ResponseEntity.notFound().build();
    } catch (Exception e){
      return ResponseEntity.internalServerError().build();
    }

  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id){

    try{
      CaixaResponseDto response = service.findById(id);
      return ResponseEntity.ok(response);
    } catch (RuntimeException e){
      return ResponseEntity.notFound().build();
    } catch (Exception e){
      return ResponseEntity.internalServerError().build();
    }

  }

  @GetMapping("/filtro-por-datas")
  public ResponseEntity<?> findByDataLancamentoBetween(
          @RequestParam(required = true) LocalDate dataInicial,
          @RequestParam(required = true) LocalDate dataFinal){

    List<CaixaResponseDto> response = service.findByDataLancamentoBetween(dataInicial, dataFinal);

    return ResponseEntity.ok(response);

  }

  @GetMapping("/filtro-por-tipo-e-datas")
  public ResponseEntity<?> findByTipoLancamentoAndDataLancamentoBetween(
          @RequestParam(required = true) String tipoLancamento,
          @RequestParam(required = true) LocalDate dataInicial,
          @RequestParam(required = true) LocalDate dataFinal){

    List<CaixaResponseDto> response = service.findByTipoLancamentoAndDataLancamentoBetween(tipoLancamento, dataInicial, dataFinal);

    return ResponseEntity.ok(response);

  }

  @GetMapping("/soma-das-entradas-por-datas")
  public ResponseEntity<?> sumByEntradasAndDataLancamentoBetween(
          @RequestParam(required = true) LocalDate dataInicial,
          @RequestParam(required = true) LocalDate dataFinal){

    BigDecimal response = service.somaDasEntradas(dataInicial, dataFinal);

    return ResponseEntity.ok(response);

  }

  @GetMapping("/soma-das-saidas-por-datas")
  public ResponseEntity<?> sumBySaidasAndDataLancamentoBetween(
          @RequestParam(required = true) LocalDate dataInicial,
          @RequestParam(required = true) LocalDate dataFinal){

    BigDecimal response = service.somaDasSaidas(dataInicial, dataFinal);

    return ResponseEntity.ok(response);

  }

  @GetMapping("/saldo-por-datas")
  public ResponseEntity<?> getSaldo(
          @RequestParam(required = true) LocalDate dataInicial,
          @RequestParam(required = true) LocalDate dataFinal){

    BigDecimal response = service.subtrairEntradasPelasSaidas(dataInicial, dataFinal);

    return ResponseEntity.ok(response);

  }


}
