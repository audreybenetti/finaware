package br.com.finaware.repository.dao;

import br.com.finaware.repository.dao.entity.Aplicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AplicacaoDAO extends JpaRepository<Aplicacao, Integer> {
}
