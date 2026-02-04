package br.loja.pitiquinho.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "tb_cartoes")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Long id;

    @NotBlank
    @Column(name = "ds_numero")
    private String numero;

    @NotBlank
    @Column(name = "ds_nome")
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{2}/\\d{2}", message = "Validade deve ser MM/AA")
    @Column(name = "ds_validade")
    private String validade;

    @NotBlank
    @Column(name = "ds_bandeira")
    private String bandeira;

    @NotBlank
    @Column(name = "ds_cvv")
    private String cvv;

    @NotNull
    @Column(name = "usuario_fk")
    private Long usuarioId;

    @ManyToOne
    @JoinColumn(name = "usuario_fk", insertable = false, updatable = false)
    private Usuario usuario;

    @Transient
    private String nomeTitular;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getValidade() { return validade; }
    public void setValidade(String validade) { this.validade = validade; }

    public String getBandeira() { return bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = bandeira; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }
}
