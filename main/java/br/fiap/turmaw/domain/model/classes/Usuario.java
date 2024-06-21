package main.java.br.fiap.turmaw.domain.model.classes;

public abstract class Usuario {
    private String nome;
    private String tipo;
    private String senha;

    public Usuario(String nome, String tipo, String senha) {
        this.nome = nome;
        this.tipo = tipo;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSenha() {
        return senha;
    }
}
