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

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

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
    private List<String> notificacoes;

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
        this.notificacoes = new ArrayList<>();
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

    public List<String> getNotificacoes() {
        return notificacoes;
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

    public void mudarCpf(String novoCpf){
        this.cpf = novoCpf;
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
        this.novaNotificacaoCompra(ingresso.getRecibo());
        this.ingressos.sort(Comparator.comparing(Ingresso::getData));
    }

    /**
     *
     * @param ingresso Ingresso a ser cancelado pelo usuário
     */
    public void cancelarIngresso(Ingresso ingresso){
        ingressos.remove(ingresso);
    }

    private void addNotificacao(String notificacao){
        if(notificacao != null) {
            this.notificacoes.add(0, notificacao);
        }
    }

    public void novaNotificacaoCompra(Recibo recibo){
        this.addNotificacao(recibo.toString());
    }

    public void novasNotificacoesEvento(){
        LocalDate hoje = LocalDate.now();
        for(Ingresso ingresso: this.ingressos){
            Period diferenca = Period.between(hoje, ingresso.getData());
            int mesesDeDiferenca = Math.abs(diferenca.getMonths());
            int anosDeDiferenca = Math.abs(diferenca.getYears());
            mesesDeDiferenca += anosDeDiferenca * 12;
            int diasDeDiferenca = Math.abs(diferenca.getDays());

            if(mesesDeDiferenca <= 1 && diasDeDiferenca <= 31){
                this.addNotificacao("Seu evento "+ingresso.getNomeEvento()+" está chegando em " + diasDeDiferenca + " dias");
            }
        }
        this.limpaNotificacoes();
    }

    public void limpaNotificacoes(){
        if (!(this.notificacoes instanceof ArrayList)) {
            this.notificacoes = new ArrayList<>(this.notificacoes);
        }
        this.notificacoes = this.notificacoes.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        if(this.notificacoes.size()>50){
            this.notificacoes.subList(51, this.notificacoes.size()).clear();
        }
    }
}
