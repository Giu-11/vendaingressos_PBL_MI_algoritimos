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


    public Usuario(String email, String cpf, String nome, String senha, String login) {
        this.email = email;
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.login = login;
        this.admin = false;
        this.ingressos = new ArrayList<>();
    }

    public Usuario(String email, String cpf, String nome, String senha, String login, boolean admin) {
        this.email = email;
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.login = login;
        this.admin = admin;
        this.ingressos = new ArrayList<>();
    }

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

    public void adicionarIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    public void cancelarIngresso(String idIngresso){
        int i=0;
        boolean encontado = false;
        while((i < (ingressos.size()-1)) || (!encontado)){
            if (!Objects.equals(idIngresso, ingressos.get(i).getId())){
                encontado = true;
                ingressos.remove(i);
            }else{
                i++;
            }
        }
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean login(String johndoe, String senha123) {
        //oq isso faz?
    }
}
