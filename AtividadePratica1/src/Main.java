import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        SistemaDocumentos sistema = new SistemaDocumentos();
        ControleAcesso controle = new ControleAcesso();

        Usuario admin = new Usuario("Rodinei", "rodinei", Perfil.ADMIN);
        Usuario editor = new Usuario("Cristovão", "cristovao", Perfil.EDITOR);
        Usuario visualizador = new Usuario("Pinto", "pinto", Perfil.VISUALIZADOR);

        System.out.println("    ---Sistema RedeMaxi---");

        System.out.println("Informe seu nome:");
        String nome = input.nextLine();

        System.out.println("Informe sua senha:");
        String senha = input.nextLine();
        
        if (nome.equals(admin.getNome()) && senha.equals(admin.getSenha())) {
        } else if (nome.equals(editor.getNome()) && senha.equals(admin.getSenha())) {
        } else if (nome.equals(visualizador.getNome()) && senha.equals(visualizador.getSenha())) {
        } else {
            System.out.println("Usuário ou senha inválidos.");
            return;
        }

        if (nome.equals(admin.getNome())) {
            System.out.println("Bem-vindo, " + admin.getNome() + "! (ADMINISTRADOR)");
        } else if (nome.equals(editor.getNome())) {
            System.out.println("Bem-vindo, " + editor.getNome() + "! (EDITOR)");
        } else if (nome.equals(visualizador.getNome())) {
            System.out.println("Bem-vindo, " + visualizador.getNome() + "! (VISUALIZADOR)");
        }
    }
}
