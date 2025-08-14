public class Usuario {
    String nome;
    String senha;
    Perfil perfil;

    public Usuario(String nome, String senha, Perfil perfil) {
        this.nome = nome;
        this.senha = senha;
        this.perfil = perfil;
    }
}
