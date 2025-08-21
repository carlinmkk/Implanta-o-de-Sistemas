import java.util.HashMap;

public class SistemaDocumentos {
    private HashMap<String, String> documentos = new HashMap<>();

    public void criarDocumento(String nome) {
        documentos.put(nome, "");
    }

    public void listarDocumentos() {
        if (documentos.isEmpty()) {
            System.out.println("Nenhum documento cadastrado.");
        } else {
            System.out.println("Documentos disponíveis:");
            for (String nome : documentos.keySet()) {
                System.out.println("- " + nome);
            }
        }
    }

    public String lerDocumento(String nome) {
        return documentos.get(nome);
    }

    public void editarDocumento(String nome, String conteudo) {
        documentos.put(nome, conteudo);
    }

    public boolean existeDocumento(String nome) {
        return documentos.containsKey(nome);
    }
    
    public void excluirDocumento(String nome) {
        documentos.remove(nome);
    }
}
