package br.com.finaware.dao;

import br.com.finaware.entity.Aplicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AplicacaoDAO extends JpaRepository<Aplicacao, Integer> {
}
