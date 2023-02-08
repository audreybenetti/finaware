package br.com.finaware.repository.dao;

import br.com.finaware.repository.dao.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoDAO extends JpaRepository<Lancamento, Integer> {
}
