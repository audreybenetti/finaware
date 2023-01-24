package br.com.finaware.dao;

import br.com.finaware.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoDAO extends JpaRepository<Lancamento, Integer> {
}
