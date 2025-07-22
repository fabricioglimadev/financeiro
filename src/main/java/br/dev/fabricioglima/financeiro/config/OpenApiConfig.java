/*
 http://localhost:8080/v3/api-docs
 http://localhost:8080/swagger-ui/index.html#/caixa-controller/healthCheck
*/

package br.dev.fabricioglima.financeiro.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  OpenAPI customOpenAPI(){
    return new OpenAPI()
            .info(new Info()
                    .title("API Finaceiro")
                    .version("v1")
                    .description("REST API Financeiro")
                    .termsOfService("https://fabricioglima.dev.br/financeiro")
                    .license(new License()
                            .name("Apache 2.0")
                            .url("https://fabricioglima.dev.br/financeiro")
                    )
            );
  }

}
