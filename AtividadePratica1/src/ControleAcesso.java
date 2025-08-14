import java.util.*;

public class ControleAcesso {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();

        usuarios.add(new Usuario(input.next(), input.next(), Perfil.ADMIN));
        usuarios.add(new Usuario(input.next(), input.next(), Perfil.EDITOR));
        usuarios.add(new Usuario(input.next(), input.next(), Perfil.VISUALIZADOR));

    }
}
