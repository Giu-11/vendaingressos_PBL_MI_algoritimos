package vendaingressos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Usuario {
    private String login;
    private String senha;
    private String nome;
    private String cpf;
    private String email;
    private List<Ingresso> ingressos;
    private final boolean admin;

    //Construtor
    public Usuario(String login, String senha, String nome, String cpf, String email, boolean admin) {
        this.email = email;
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.login = login;
        this.admin = admin;
        this.ingressos = new ArrayList<>();
    }

    //Getters
    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    //Setters
    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean login(String login, String senha) {
        return (Objects.equals(login, this.login)) && (Objects.equals(senha, this.senha));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return admin == usuario.admin && Objects.equals(login, usuario.login) && Objects.equals(nome, usuario.nome) && Objects.equals(cpf, usuario.cpf) && Objects.equals(email, usuario.email) && Objects.equals(ingressos, usuario.ingressos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, nome, cpf, email, ingressos, admin);
    }

    public void adicionarIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    public void cancelarIngresso(Ingresso ingresso){
        ingressos.remove(ingresso);
    }
}
