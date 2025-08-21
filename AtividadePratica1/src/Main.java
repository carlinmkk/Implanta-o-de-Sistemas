import java.util.Scanner;

public class Main {

    public static void limparConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível limpar o console.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        SistemaDocumentos sistema = new SistemaDocumentos();
        ControleAcesso controle = new ControleAcesso();

        Usuario admin = new Usuario("Carlos", "adm123", Perfil.ADMIN);
        Usuario editor = new Usuario("Rafael", "edi123", Perfil.EDITOR);
        Usuario visualizador = new Usuario("Magno", "visu123", Perfil.VISUALIZADOR);

        while (true) { // loop infinito até usuário decidir sair
            limparConsole();
            System.out.println("\n    ---Sistema RedeMaxi---");

            System.out.print("Informe seu nome: ");
            String nome = input.nextLine();

            System.out.print("Informe sua senha: ");
            String senha = input.nextLine();

            Usuario usuarioAtual = null;

            if (nome.equals(admin.getNome()) && senha.equals(admin.getSenha())) {
                usuarioAtual = admin;
            } else if (nome.equals(editor.getNome()) && senha.equals(editor.getSenha())) {
                usuarioAtual = editor;
            } else if (nome.equals(visualizador.getNome()) && senha.equals(visualizador.getSenha())) {
                usuarioAtual = visualizador;
            } else {
                System.out.println("Usuário ou senha inválidos.");
                Thread.sleep(2000); // espera 2 segundos
                continue; // volta pro login
            }

            limparConsole();
            System.out.println("Bem-vindo, " + usuarioAtual.getNome() + "! (" + usuarioAtual.getPerfil() + ")");
            Thread.sleep(1000);

            // Menu dinâmico
            System.out.println("\nSelecione uma opção:");
            if (controle.podeEditar(usuarioAtual)) {
                System.out.println("1. Editar Documento");
            }
            if (controle.podeVisualizar(usuarioAtual)) {
                System.out.println("2. Visualizar Documento");
            }
            if (controle.podeExcluir(usuarioAtual)) {
                System.out.println("3. Excluir Documento");
            }
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            int opcao = input.nextInt();
            input.nextLine(); // limpar buffer

            switch (opcao) {
                case 1: // Editar
                    if (controle.podeEditar(usuarioAtual)) {
                        sistema.listarDocumentos();
                        System.out.println("Digite o nome do documento que deseja editar:");
                        String docParaEditar = input.nextLine();

                        if (!docParaEditar.equals("Relatorio Financeiro")
                                && !docParaEditar.equals("Plano de Seguranca")
                                && !docParaEditar.equals("Manual do Usuario")) {
                            System.out.println("Documento não encontrado.");
                            break;
                        }

                        System.out.println(usuarioAtual.getNome() + " está editando o documento: " + docParaEditar + ".");
                        Thread.sleep(1000);
                        System.out.println("Documento editado com sucesso.");
                    } else {
                        System.out.println("Você não tem permissão para essa ação.");
                    }
                    break;

                case 2: // Visualizar
                    if (controle.podeVisualizar(usuarioAtual)) {
                        sistema.listarDocumentos();
                        System.out.println("Digite o nome do documento que deseja visualizar:");
                        String docParaVer = input.nextLine();
                        sistema.visualizarDoc(usuarioAtual, docParaVer);
                    } else {
                        System.out.println("Você não tem permissão para essa ação.");
                    }
                    break;

                case 3: // Excluir
                    if (controle.podeExcluir(usuarioAtual)) {
                        sistema.listarDocumentos();
                        System.out.println("Digite o nome do documento que deseja excluir:");
                        String docParaExcluir = input.nextLine();
                        sistema.excluirDoc(usuarioAtual, docParaExcluir);
                    } else {
                        System.out.println("Você não tem permissão para essa ação.");
                    }
                    break;

                case 0: // Sair do sistema
                    System.out.println("Saindo do sistema...");
                    Thread.sleep(2000);
                    input.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println("\nVoltando ao login...");
            Thread.sleep(2000);
        }
    }
}
