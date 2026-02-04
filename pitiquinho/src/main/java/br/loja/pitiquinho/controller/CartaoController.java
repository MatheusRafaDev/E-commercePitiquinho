package br.loja.pitiquinho.controller;

import br.loja.pitiquinho.model.Cartao;
import br.loja.pitiquinho.model.Usuario;
import br.loja.pitiquinho.repository.CartaoRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @GetMapping
    public String listarCartoes(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        List<Cartao> cartoes = cartaoRepository.findByUsuarioId(usuario.getId());
        model.addAttribute("cartoes", cartoes);
        model.addAttribute("cartao", new Cartao());
        return "cartoes";
    }

    @PostMapping("/adicionar")
    public String adicionarCartao(@Valid @ModelAttribute("cartao") Cartao cartao,
                                  BindingResult result,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        cartao.setUsuarioId(usuario.getId());
        cartaoRepository.save(cartao);
        redirectAttributes.addFlashAttribute("success", "Cartão adicionado com sucesso");

        return "redirect:/cartoes";
    }

    @PostMapping("/remover/{id}")
    public String removerCartao(@PathVariable Long id,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";

        Cartao cartao = cartaoRepository.findById(id).orElseThrow();
        if (!cartao.getUsuarioId().equals(usuario.getId())) {
            redirectAttributes.addFlashAttribute("error", "Cartão não pertence a este usuário");
            return "redirect:/cartoes";
        }

        cartaoRepository.delete(cartao);
        redirectAttributes.addFlashAttribute("success", "Cartão removido com sucesso");

        return "redirect:/cartoes";
    }
}
