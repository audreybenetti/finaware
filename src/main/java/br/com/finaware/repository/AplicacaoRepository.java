package br.com.finaware.repository;

import br.com.finaware.entity.Aplicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AplicacaoRepository extends JpaRepository<Aplicacao, Integer> {
}
