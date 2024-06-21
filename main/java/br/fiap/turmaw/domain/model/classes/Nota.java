package main.java.br.fiap.turmaw.domain.model.classes;

public class Nota {
    private Aluno aluno;
    private Curso curso;
    private double valor;

    public Nota(Aluno aluno, Curso curso, double valor) {
        this.aluno = aluno;
        this.curso = curso;
        this.valor = valor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public double getValor() {
        return valor;
    }
}
