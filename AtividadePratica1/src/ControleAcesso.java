public class ControleAcesso {

    public static boolean podeEditar(Usuario usuario) {
        if (usuario.getPerfil() == Perfil.ADMIN || usuario.getPerfil() == Perfil.EDITOR) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean podeVisualizar(Usuario usuario) {
        return true;

    }

    public static boolean podeExcluir(Usuario usuario) {
        if (usuario.getPerfil() == Perfil.ADMIN) {
            return true;
        } else {
            return false;
        }
    }
}
