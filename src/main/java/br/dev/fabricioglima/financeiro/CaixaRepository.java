package br.dev.fabricioglima.financeiro;

import br.dev.fabricioglima.financeiro.entity.CaixaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaixaRepository extends JpaRepository<CaixaEntity, Long> {
}
