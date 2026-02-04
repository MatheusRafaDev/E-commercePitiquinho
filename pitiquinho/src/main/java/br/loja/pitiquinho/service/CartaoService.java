package br.loja.pitiquinho.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.loja.pitiquinho.model.Cartao;
import br.loja.pitiquinho.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    public List<Cartao> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public Cartao salvar(Cartao cartao) {
        return repository.save(cartao);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
