/*******************************************************************************************
 Autor: Giulia Aguiar Loula
 Componente Curricular: EXA863 - MI - PROGRAMAÇÃO
 Concluído em: 18/10/2024
 Declaro que este código foi elaborado por mim de forma individual e não contêm nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

 ********************************************************************************************/

package vendaingressos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Usuário do sistema, pode ser administrador
 */
public class Usuario {
    private final String login;
    private String senha;
    private String nome;
    private String cpf;
    private String email;
    private List<Ingresso> ingressos;
    private final boolean admin;

    //Construtor

    /**
     * @param login Login do usuário
     * @param senha Senha do usuário
     * @param nome nome do usuário
     * @param cpf cpf do usuário
     * @param email email do usuário
     * @param admin se o usuário é um administrator
     */
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

    /**
     * @return se o usuário é um administrador
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param novoNome novo nome do usuário
     */
    public void mudarNome(String novoNome){
        this.nome = novoNome;
    }

    /**
     *
     * @param novoEmail novo e-mail do usuário
     */
    public void mudarEmail(String novoEmail){
        this.email= novoEmail;
    }

    /**
     * troca a senha caso a senha antiga esteja correta
     * @param senha senha antiga do usuário
     * @param novaSenha senha nova do usuário
     * @return retorna se foi possível trocar a senha
     */
    public boolean mudarSenha(String senha, String novaSenha){
        if (login(this.login, senha)){
            this.senha = novaSenha;
            return true;
        }
        return false;
    }

    /**
     *
     * @param login Login do usuário
     * @param senha senha do usuário
     * @return Se o login e senha estão corretos
     */
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

    /**
     *
     * @param ingresso Ingresso comprado pelo usuário
     */
    public void adicionarIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    /**
     *
     * @param ingresso Ingresso a ser cancelado pelo usuário
     */
    public void cancelarIngresso(Ingresso ingresso){
        ingressos.remove(ingresso);
    }
}
