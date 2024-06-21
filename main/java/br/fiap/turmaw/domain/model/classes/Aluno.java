package main.java.br.fiap.turmaw.domain.model.classes;

public class Aluno extends Usuario {
    private int matricula;

    public Aluno(String nome, int matricula, String senha) {
        super(nome, "Aluno", senha);
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }
}
