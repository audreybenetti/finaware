package br.com.finaware.dao;

import br.com.finaware.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoDAO extends JpaRepository<Produto, Integer> {
}
