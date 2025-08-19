public class ControleAcesso {

    public static boolean podeEditar(Usuario usuario) {
        if (usuario.getPerfil() == Perfil.ADMIN || usuario.getPerfil() == Perfil.EDITOR) {
            System.out.println("Acesso Permitido.\n" + usuario.getNome() + " pode editar o documento.");
            return true;
        } else {
            System.out.println("Acesso Negado.\n" + usuario.getNome() + " NÃO pode editar o documento.");
            return false;
        }
    }

    public static boolean podeVisualizar(Usuario usuario) {
        System.out.println("Acesso Permitido.\n" + usuario.getNome() + " pode visualizar o documento.");
        return true;

    }

    public static boolean podeExcluir(Usuario usuario) {
        if (usuario.getPerfil() == Perfil.ADMIN) {
            System.out.println("Acesso Permitido.\n" + usuario.getNome() + " pode excluir o documento.");
            return true;
        } else {
            System.out.println("Acesso Negado.\n" + usuario.getNome() + " NÃO pode excluir o documento.");
            return false;
        }
    }
}
