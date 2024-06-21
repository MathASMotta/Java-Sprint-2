package main.java.br.fiap.turmaw.domain.model.classes;

import java.util.List;
import java.util.ArrayList;

public class Curso {
    private String nome;
    private List<Professor> professores;
    private List<Aluno> alunos;
    private List<Material> materiais;

    public Curso(String nome) {
        this.nome = nome;
        this.professores = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.materiais = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Material> getMateriais() {
        return materiais;
    }

    public void adicionarMaterial(Material material) {
        materiais.add(material);
    }
}
