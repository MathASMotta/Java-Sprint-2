package main.java.br.fiap.turmaw.domain.model.classes;

public class Material {
    private String titulo;
    private String conteudo;

    public Material(String titulo, String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }
}
