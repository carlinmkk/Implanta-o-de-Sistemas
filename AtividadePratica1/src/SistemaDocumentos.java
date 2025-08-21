import java.util.ArrayList;
import java.util.List;

public class SistemaDocumentos {

    private List<String> documentos;

    public SistemaDocumentos() {
        documentos = new ArrayList<>();
        documentos.add("Relatorio Financeiro");
        documentos.add("Plano de Seguranca");
        documentos.add("Manual do Usuario");
    }

    public void visualizarDoc(Usuario usuario, String documento) {
        if (ControleAcesso.podeVisualizar(usuario)) {
            System.out.println(usuario.getNome() + " está editando o documento:" + documento + ".");
        }
    }

    public void editarDoc(Usuario usuario, String documento) {
        if (ControleAcesso.podeEditar(usuario)) {
            System.out.println(usuario.getNome() + " está editando o documento:" + documento + ".");
        }
    }

    public void excluirDoc(Usuario usuario, String documento) {
        if (ControleAcesso.podeExcluir(usuario)) {
            System.out.println(usuario.getNome() + " está excluindo o documento:" + documento + ".");
        }
    }

    public void listarDocumentos() {
        System.out.println("Documentos disponíveis:");
        for (String doc : documentos) {
            System.out.println("- " + doc);
        }
    }
}
