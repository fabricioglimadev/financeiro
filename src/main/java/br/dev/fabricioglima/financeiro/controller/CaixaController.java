package br.dev.fabricioglima.financeiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

  @GetMapping("/healthcheck")
  public String healthCheck(){
    return "API está no ar";
  }



}
