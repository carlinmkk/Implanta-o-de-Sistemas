
public class SistemaDocumentos {

    void visualizarDoc(Usuario usuario) {
        System.out.println(usuario.nome + " visualizou o documento.");
    }

    void editarDoc(Usuario usuario) {
        if (usuario.perfil == Perfil.ADMIN || usuario.perfil == Perfil.EDITOR) {

        }
    }
}
