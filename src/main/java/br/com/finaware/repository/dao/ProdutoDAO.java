package br.com.finaware.repository.dao;

import br.com.finaware.repository.dao.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoDAO extends JpaRepository<Produto, Integer> {
}
