package main.java.br.fiap.turmaw.domain.model.classes;

public class Professor extends Usuario {
    private String departamento;

    public Professor(String nome, String departamento, String senha) {
        super(nome, "Professor", senha);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }
}
