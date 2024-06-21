package main.java.br.fiap.turmaw.domain.model.classes;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorCursos {
    private List<Curso> cursos;

    public GerenciadorCursos() {
        this.cursos = new ArrayList<>();
    }

    public void cadastrarCurso(Curso curso) {
        cursos.add(curso);
    }

    public Curso consultarCurso(String nome) {
        for (Curso curso : cursos) {
            if (curso.getNome().equalsIgnoreCase(nome)) {
                return curso;
            }
        }
        return null;
    }

    public List<Curso> listarCursos() {
        return cursos;
    }

    public void adicionarProfessorAoCurso(String nomeCurso, Professor professor) {
        Curso curso = consultarCurso(nomeCurso);
        if (curso != null) {
            curso.adicionarProfessor(professor);
        } else {
            curso = new Curso(nomeCurso);
            curso.adicionarProfessor(professor);
            cadastrarCurso(curso);
        }
    }
}
