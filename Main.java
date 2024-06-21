import main.java.br.fiap.turmaw.domain.model.classes.*;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
        GerenciadorCursos gerenciadorCursos = new GerenciadorCursos();
        GerenciadorNotas gerenciadorNotas = new GerenciadorNotas();
        Scanner scanner = new Scanner(System.in);
        Usuario usuarioLogado = null;
        int opcao;

        // Cadastrando alguns usuários e cursos para teste
        gerenciadorUsuarios.cadastrarUsuario(new Professor("João Silva", "Matemática", "1234"));
        gerenciadorUsuarios.cadastrarUsuario(new Aluno("Ana Souza", 123, "5678"));

        Professor professorTeste = (Professor) gerenciadorUsuarios.consultarUsuario("João Silva");
        gerenciadorCursos.adicionarProfessorAoCurso("Matemática", professorTeste);

        while (true) {
            usuarioLogado = null;
            System.out.println("Bem-vindo ao Sistema de Gerenciamento");
            System.out.println("1. Login");
            System.out.println("2. Cadastrar Usuário");
            System.out.println("0. Sair");

            while (true) {
                System.out.print("Escolha uma opção: ");
                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                }
            }

            if (opcao == 1) {
                while (usuarioLogado == null) {
                    System.out.print("Digite seu nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite sua senha: ");
                    String senha = scanner.nextLine();
                    usuarioLogado = gerenciadorUsuarios.login(nome, senha);
                    if (usuarioLogado == null) {
                        System.out.println("Nome ou senha incorretos. Tente novamente.");
                        System.out.print("1. Tentar novamente\n0. Voltar\nEscolha uma opção: ");
                        int subOpcao;
                        while (true) {
                            try {
                                subOpcao = Integer.parseInt(scanner.nextLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida. Por favor, digite um número.");
                            }
                        }
                        if (subOpcao == 0) {
                            break;
                        }
                    }
                }
                if (usuarioLogado == null) {
                    continue;
                }
            } else if (opcao == 2) {
                System.out.print("Digite o nome: ");
                String nome = scanner.nextLine();
                System.out.print("Digite o tipo (Professor/Aluno): ");
                String tipo = scanner.nextLine();
                System.out.print("Digite a senha: ");
                String senha = scanner.nextLine();
                if (tipo.equalsIgnoreCase("Professor")) {
                    System.out.print("Digite o departamento: ");
                    String departamento = scanner.nextLine();
                    gerenciadorUsuarios.cadastrarUsuario(new Professor(nome, departamento, senha));
                    Professor professor = (Professor) gerenciadorUsuarios.consultarUsuario(nome);
                    gerenciadorCursos.adicionarProfessorAoCurso(departamento, professor);
                } else if (tipo.equalsIgnoreCase("Aluno")) {
                    int matricula;
                    while (true) {
                        System.out.print("Digite a matrícula: ");
                        try {
                            matricula = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Por favor, digite um número.");
                        }
                    }
                    gerenciadorUsuarios.cadastrarUsuario(new Aluno(nome, matricula, senha));
                } else {
                    System.out.println("Tipo de usuário inválido.");
                }
            } else if (opcao == 0) {
                System.out.println("Saindo...");
                scanner.close();
                return;
            } else {
                System.out.println("Opção inválida.");
            }

            if (usuarioLogado != null) {
                while (true) {
                    System.out.println("Bem-vindo, " + usuarioLogado.getNome() + " (" + usuarioLogado.getTipo() + ")");
                    System.out.println("Sistema de Gerenciamento");
                    if (usuarioLogado instanceof Professor) {
                        System.out.println("1. Listar Alunos");
                        System.out.println("2. Consultar Curso");
                        System.out.println("3. Adicionar Aluno ao Curso");
                        System.out.println("4. Adicionar Material ao Curso");
                        System.out.println("5. Adicionar Nota ao Aluno");
                        System.out.println("0. Sair");
                    } else if (usuarioLogado instanceof Aluno) {
                        System.out.println("1. Consultar Curso");
                        System.out.println("2. Consultar Usuário");
                        System.out.println("3. Ver Material");
                        System.out.println("4. Ver Notas");
                        System.out.println("0. Sair");
                    }

                    while (true) {
                        System.out.print("Escolha uma opção: ");
                        try {
                            opcao = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Por favor, digite um número.");
                        }
                    }

                    if (opcao == 0) {
                        break;
                    }

                    switch (opcao) {
                        case 1:
                            if (usuarioLogado instanceof Professor) {
                                System.out.println("Lista de Alunos:");
                                for (Usuario u : gerenciadorUsuarios.listarUsuarios()) {
                                    if (u instanceof Aluno) {
                                        System.out.println(u.getNome() + " - " + u.getTipo());
                                    }
                                }
                            } else if (usuarioLogado instanceof Aluno) {
                                System.out.print("Digite o nome do curso: ");
                                String nomeCurso = scanner.nextLine();
                                Curso curso = gerenciadorCursos.consultarCurso(nomeCurso);
                                if (curso != null) {
                                    System.out.println("Nome do Curso: " + curso.getNome());
                                    System.out.println("Professores: ");
                                    for (Professor professor : curso.getProfessores()) {
                                        System.out.println(professor.getNome());
                                    }
                                    System.out.println("Alunos Inscritos:");
                                    for (Aluno aluno : curso.getAlunos()) {
                                        System.out.println(aluno.getNome());
                                    }
                                    System.out.println("Materiais do Curso:");
                                    for (Material material : curso.getMateriais()) {
                                        System.out.println(material.getTitulo() + " - " + material.getConteudo());
                                    }
                                } else {
                                    System.out.println("Curso não encontrado.");
                                }
                            }
                            break;

                        case 2:
                            if (usuarioLogado instanceof Professor) {
                                System.out.print("Digite o nome do curso: ");
                                String nomeCurso = scanner.nextLine();
                                Curso curso = gerenciadorCursos.consultarCurso(nomeCurso);
                                if (curso != null) {
                                    System.out.println("Nome do Curso: " + curso.getNome());
                                    System.out.println("Professores: ");
                                    for (Professor professor : curso.getProfessores()) {
                                        System.out.println(professor.getNome());
                                    }
                                    System.out.println("Alunos Inscritos:");
                                    for (Aluno aluno : curso.getAlunos()) {
                                        System.out.println(aluno.getNome());
                                    }
                                    System.out.println("Materiais do Curso:");
                                    for (Material material : curso.getMateriais()) {
                                        System.out.println(material.getTitulo() + " - " + material.getConteudo());
                                    }
                                } else {
                                    System.out.println("Curso não encontrado.");
                                }
                            } else if (usuarioLogado instanceof Aluno) {
                                System.out.println("Informações do Aluno:");
                                System.out.println("Nome: " + usuarioLogado.getNome());
                                System.out.println("Matrícula: " + ((Aluno) usuarioLogado).getMatricula());
                            }
                            break;

                        case 3:
                            if (usuarioLogado instanceof Professor) {
                                System.out.print("Digite o nome do curso: ");
                                String nomeCurso = scanner.nextLine();
                                Curso curso = gerenciadorCursos.consultarCurso(nomeCurso);
                                if (curso != null) {
                                    System.out.print("Digite o nome do aluno: ");
                                    String nomeAluno = scanner.nextLine();
                                    Aluno aluno = (Aluno) gerenciadorUsuarios.consultarUsuario(nomeAluno);
                                    if (aluno != null && aluno.getTipo().equals("Aluno")) {
                                        curso.adicionarAluno(aluno);
                                    } else {
                                        System.out.println("Aluno não encontrado ou inválido.");
                                    }
                                } else {
                                    System.out.println("Curso não encontrado.");
                                }
                            } else if (usuarioLogado instanceof Aluno) {
                                System.out.print("Digite o nome do curso: ");
                                String nomeCurso = scanner.nextLine();
                                Curso curso = gerenciadorCursos.consultarCurso(nomeCurso);
                                if (curso != null) {
                                    System.out.println("Materiais do Curso:");
                                    for (Material material : curso.getMateriais()) {
                                        System.out.println(material.getTitulo() + " - " + material.getConteudo());
                                    }
                                } else {
                                    System.out.println("Curso não encontrado.");
                                }
                            }
                            break;

                        case 4:
                            if (usuarioLogado instanceof Professor) {
                                System.out.print("Digite o nome do curso: ");
                                String nomeCurso = scanner.nextLine();
                                Curso curso = gerenciadorCursos.consultarCurso(nomeCurso);
                                if (curso != null) {
                                    System.out.print("Digite o título do material: ");
                                    String tituloMaterial = scanner.nextLine();
                                    System.out.print("Digite o conteúdo do material: ");
                                    String conteudoMaterial = scanner.nextLine();
                                    Material material = new Material(tituloMaterial, conteudoMaterial);
                                    curso.adicionarMaterial(material);
                                } else {
                                    System.out.println("Curso não encontrado.");
                                }
                            } else if (usuarioLogado instanceof Aluno) {
                                List<Nota> notas = gerenciadorNotas.consultarNotas((Aluno) usuarioLogado);
                                if (notas.isEmpty()) {
                                    System.out.println("Você não tem notas cadastradas.");
                                } else {
                                    System.out.println("Suas Notas:");
                                    for (Nota nota : notas) {
                                        System.out.println("Curso: " + nota.getCurso().getNome() + " - Nota: " + nota.getValor());
                                    }
                                }
                            }
                            break;

                        case 5:
                            if (usuarioLogado instanceof Professor) {
                                System.out.print("Digite o nome do curso: ");
                                String nomeCurso = scanner.nextLine();
                                Curso curso = gerenciadorCursos.consultarCurso(nomeCurso);
                                if (curso != null) {
                                    System.out.print("Digite o nome do aluno: ");
                                    String nomeAluno = scanner.nextLine();
                                    Aluno aluno = (Aluno) gerenciadorUsuarios.consultarUsuario(nomeAluno);
                                    if (aluno != null && aluno.getTipo().equals("Aluno")) {
                                        boolean entradaValida = false;
                                        while (!entradaValida) {
                                            System.out.print("Digite a nota: ");
                                            if (scanner.hasNextDouble()) {
                                                double nota = scanner.nextDouble();
                                                scanner.nextLine(); // Limpar buffer
                                                Nota notaAluno = new Nota(aluno, curso, nota);
                                                gerenciadorNotas.adicionarNota(notaAluno);
                                                System.out.println("Nota adicionada com sucesso.");
                                                entradaValida = true; // Saindo do loop
                                            } else {
                                                System.out.println("Entrada inválida. Por favor, use vírgula como separador decimal.");
                                                scanner.nextLine(); // Limpar buffer
                                            }
                                        }
                                    } else {
                                        System.out.println("Aluno não encontrado ou inválido.");
                                    }
                                } else {
                                    System.out.println("Curso não encontrado.");
                                }
                            }
                            break;

                        default:
                            System.out.println("Opção inválida.");
                    }

                    System.out.println("Pressione Enter para voltar ao menu.");
                    scanner.nextLine();
                }
            }
        }
    }
}
