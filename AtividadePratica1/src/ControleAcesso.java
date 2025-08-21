import java.util.ArrayList;

public class ControleAcesso {
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario login(String nome, String senha) {
        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nome) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
}
