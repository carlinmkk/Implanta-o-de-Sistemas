public class Usuario {
    private String nome;
    private String senha;
    private Perfil perfil;

    public Usuario(String nome, String senha, Perfil perfil) {
        this.nome = nome;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getSenha() {
        return senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public boolean podeEditar() {
        return perfil == Perfil.ADMIN || perfil == Perfil.EDITOR;
    }
    
    @Override
    public String toString() {
        return "\n\n     Usuario\n_________________" +
                "\nNome:" + nome +
                "\nSenha:" + senha +
                "\nPerfil:" + perfil;
    }
}
