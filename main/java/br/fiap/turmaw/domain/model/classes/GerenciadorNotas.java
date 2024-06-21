package main.java.br.fiap.turmaw.domain.model.classes;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorNotas {
    private List<Nota> notas;

    public GerenciadorNotas() {
        this.notas = new ArrayList<>();
    }

    public void adicionarNota(Nota nota) {
        notas.add(nota);
    }

    public List<Nota> consultarNotas(Aluno aluno) {
        List<Nota> notasAluno = new ArrayList<>();
        for (Nota nota : notas) {
            if (nota.getAluno().equals(aluno)) {
                notasAluno.add(nota);
            }
        }
        return notasAluno;
    }
}
