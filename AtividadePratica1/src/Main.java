import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SistemaDocumentos sistema = new SistemaDocumentos();
        ControleAcesso controle = new ControleAcesso();

        // Criação de Usuários
        controle.adicionarUsuario(new Usuario("Carlos", "admin123", Perfil.ADMIN));
        controle.adicionarUsuario(new Usuario("Rafael", "edt123", Perfil.EDITOR));
        controle.adicionarUsuario(new Usuario("Magno", "vis123", Perfil.VISUALIZADOR));

        System.out.println("===== Sistema de Documentos RedeMaxi =====");

        boolean programaRodando = true;

        while (programaRodando) {
            //Login
            Usuario usuarioLogado = null;
            while (usuarioLogado == null) {
                System.out.print("Usuário: ");
                String nome = input.nextLine();
                System.out.print("Senha: ");
                String senha = input.nextLine();

                usuarioLogado = controle.login(nome, senha);

                if (usuarioLogado == null) {
                    System.out.println("Usuário ou senha inválidos. Tente novamente.");
                }
            }

            Clear();
            System.out.println("Bem-vindo, " + usuarioLogado.getNome() + " (" + usuarioLogado.getPerfil() + ")!");
            esperar(2);

            boolean sessaoAtiva = true;
            while (sessaoAtiva) {
                Clear();
                System.out.println("\n===== MENU PRINCIPAL =====");

                int contador = 1;

                // Opções básicas (todos podem)
                System.out.println(contador++ + " - Listar documentos");
                System.out.println(contador++ + " - Visualizar documento");

                int opcaoExtra = contador;

                // Se pode editar (Admin e Editor)
                if (usuarioLogado.podeEditar()) {
                    System.out.println(opcaoExtra++ + " - Editar documento");
                }

                // Se for Admin
                if (usuarioLogado.getPerfil() == Perfil.ADMIN) {
                    System.out.println(opcaoExtra++ + " - Criar documento");
                    System.out.println(opcaoExtra++ + " - Excluir documento");
                }

                // Finalizar sessão
                System.out.println(opcaoExtra++ + " - Finalizar sessão");

                // Encerrar programa
                System.out.println(opcaoExtra + " - Sair do programa");

                System.out.print("Escolha: ");
                int escolha;
                try {
                    escolha = Integer.parseInt(input.nextLine());
                } catch (Exception e) {
                    System.out.println("Opção inválida!");
                    esperar(2);
                    continue;
                }

                contador = 1;

                // 1 - Listar
                if (escolha == contador++) {
                    Clear();
                    sistema.listarDocumentos();
                    esperar(2);
                    continue;
                }

                // 2 - Visualizar
                if (escolha == contador++) {
                    System.out.print("Digite o nome do documento para visualizar: ");
                    String nomeVer = input.nextLine();
                    Clear();
                    if (sistema.existeDocumento(nomeVer)) {
                        String conteudo = sistema.lerDocumento(nomeVer);
                        System.out.println("\nConteúdo do documento '" + nomeVer + "':");
                        System.out.println(conteudo.isEmpty() ? "[Vazio]" : conteudo);
                    } else {
                        System.out.println("Documento não encontrado.");
                    }
                    esperar(2);
                    continue;
                }

                // Se pode editar
                if (usuarioLogado.podeEditar()) {
                    if (escolha == contador++) {
                        System.out.print("Digite o nome do documento para editar: ");
                        String nomeEdit = input.nextLine();
                        Clear();
                        if (sistema.existeDocumento(nomeEdit)) {
                            System.out.println("Digite o novo conteúdo: ");
                            String novoConteudo = input.nextLine();
                            sistema.editarDocumento(nomeEdit, novoConteudo);
                            System.out.println("Documento atualizado com sucesso!");
                        } else {
                            System.out.println("Documento não encontrado.");
                        }
                        esperar(2);
                        continue;
                    }
                }

                // Se for Admin
                if (usuarioLogado.getPerfil() == Perfil.ADMIN) {
                    if (escolha == contador++) {
                        System.out.print("Digite o nome do documento: ");
                        String nomeDoc = input.nextLine();
                        Clear();
                        sistema.criarDocumento(nomeDoc);
                        System.out.println("Documento '" + nomeDoc + "' criado com sucesso!");
                        esperar(2);
                        continue;
                    }

                    if (escolha == contador++) {
                        System.out.print("Digite o nome do documento para excluir: ");
                        String nomeExc = input.nextLine();
                        Clear();
                        if (sistema.existeDocumento(nomeExc)) {
                            sistema.excluirDocumento(nomeExc);
                            System.out.println("Documento '" + nomeExc + "' excluído com sucesso!");
                        } else {
                            System.out.println("Documento não encontrado.");
                        }
                        esperar(2);
                        continue;
                    }
                }

                // Finalizar sessão
                if (escolha == contador++) {
                    System.out.println("Sessão finalizada. Voltando para tela de login...");
                    esperar(2);
                    sessaoAtiva = false;
                    Clear();
                    continue;
                }

                // Encerrar programa
                if (escolha == contador) {
                    System.out.println("Encerrando sistema. Até logo!");
                    esperar(2);
                    sessaoAtiva = false;
                    programaRodando = false;
                    input.close();
                    Clear();
                    break;
                }

                System.out.println("Opção inválida!");
                esperar(1);
            }
        }
    }

    public static void Clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J"); // Para Linux/Mac
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível limpar a tela.");
        }
    }

    public static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
