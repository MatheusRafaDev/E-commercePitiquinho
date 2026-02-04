package br.loja.pitiquinho.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.loja.pitiquinho.model.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    List<Cartao> findByUsuarioId(Long usuarioId);
}
