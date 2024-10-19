/*******************************************************************************************
Autor: Giulia Aguiar Loula
Componente Curricular: EXA863 - MI - PROGRAMAÇÃO
Concluído em: 07/09/2024
Declaro que este código foi elaborado por mim de forma individual e não contêm nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

********************************************************************************************/

package vendaingressos;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Responsável por Controlar as outras classes
 */
public class Controller {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Evento> eventos = new ArrayList<>();

    /**
     *
     * @param login Login do usuário
     * @param senha Senha do usuário
     * @param nome nome do usuário
     * @param cpf cpf do usuário
     * @param email email do usuário
     * @param admin se o usuário é um administrator
     * @return Usuário cadastrado
     */
    public Usuario cadastrarUsuario(String login, String senha, String nome, String cpf, String email, boolean admin) {
        Repositorio repositorio = new Repositorio();

        if(!repositorio.usuarioExiste(login)) {
            Usuario novoUsuario = new Usuario(login, senha, nome, cpf, email, admin);
            repositorio.guardaUsuario(novoUsuario);
            usuarios.add(novoUsuario);
            return novoUsuario;
        }
        return null;
    }

    /**
     *
     * @param admin usuário administrador
     * @param nome nome do evento
     * @param descricao descrição do evento
     * @param data data do evento
     * @return Evento cadastrado
     *
     * @throws SecurityException Lança um erro de segurança caso um usuário não
     * administrador tentar criar um evento
     */
    public Evento cadastrarEvento(Usuario admin, String nome, String descricao, LocalDate data, int totalAssentos) {
        if(admin.isAdmin()) {
            Repositorio repositorio = new Repositorio();
            Evento novoEvento = new Evento(nome, descricao, data, totalAssentos);
            eventos.add(novoEvento);
            repositorio.guardaEvento(novoEvento);
            return novoEvento;
        } else{
            //Retorna um erro se um usuário não administrador tentar criar um evento
            throw new SecurityException("Somente administradores podem cadastrar eventos.");
        }
    }


    public boolean login(String login, String senha){
        Repositorio repositorio = new Repositorio();
        Usuario usuario = repositorio.buscaUsuario(login);
        if(usuario!=null){
            return usuario.login(login, senha);
        }
        return false;
    }

    /***
     *
     * @param usuario
     * @param nomeEvento
     * @param formaPagamento
     * @return
     */
    public Ingresso comprarIngresso(Usuario usuario, String nomeEvento, String formaPagamento) {
        Repositorio repositorio = new Repositorio();
        List<Evento> eventos = repositorio.buscaEventoNome(nomeEvento);

        //Se o evento existir realiza as ações de compra
        if(!eventos.isEmpty()){

            if (eventos.get(0).getTotalAssentos() > eventos.get(0).getAssentosComprados()) {
                Ingresso novoIngresso = new Ingresso(eventos.get(0), true, formaPagamento);
                usuario.adicionarIngresso(novoIngresso);
                eventos.get(0).compraIngresso();
                repositorio.guardaUsuario(usuario);
                repositorio.guardaEvento(eventos.get(0));
                return novoIngresso;
            }
        }
        return null;
    }


    /**
     * Retorna 'true' se o usuário possui o ingresso
     * e assim esse pode ser cancelado
     *
     * @param usuario usuário que cancelará um ingresso
     * @param ingresso ingresso a ser cancelado
     * @return se a ação pode ser feita
     */
    public boolean cancelarCompra(Usuario usuario, Ingresso ingresso) {
        //cancela um ingresso coso o usuário informado possua ele
        Repositorio repositorio = new Repositorio();

        if(usuario.getIngressos().contains(ingresso)){
            Evento evento = repositorio.buscaEventoId(ingresso.getEvento());
            evento.cancelaCompra();
            usuario.cancelarIngresso(ingresso);
            ingresso.cancelar();
            repositorio.guardaEvento(evento);
            repositorio.guardaUsuario(usuario);
            return true;
        }
        return false;
    }


    /**
     *
     * @return Lista de eventos que acontecerão no futuro
     */
    public List<Evento> listarEventosDisponiveis() {
        //Data definida como 9 de setembro para simulação do código
        //garante que os resultados dos testes sejam os esperados para as datas definidas neles
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 9);

        Repositorio repositorio = new Repositorio();

        //retorna uma lista de todos os eventos que acontecerão depois da data definida
        return repositorio.listarEventosDisponiveis();
    }


    /**
     *
     * @param usuario usuário que terá seus ingressos listados
     * @return Ingressos do usuário fornecido
     */
    public List<Ingresso> listarIngressosComprados(Usuario usuario) {
        return usuario.getIngressos();
    }

    /***
     *
     * @param idEvento id do evento que deve ser procurado
     * @return retorna o evento caso ele seja encontrado, ou null caso não
     */
    public Evento buscaEvento(String idEvento){
        //TODO busca nos arquivos
        Repositorio repositorio = new Repositorio();
        return repositorio.buscaEventoId(idEvento);
    }

    public void editaNomeUsuario(String novoNome, Usuario usuario){
        Repositorio repositorio = new Repositorio();
        usuario.mudarNome(novoNome);
        repositorio.guardaUsuario(usuario);

    }

    public void editaSenhaUsuario(String novaSenha, String senhaAntiga, Usuario usuario){
        Repositorio repositorio = new Repositorio();
        usuario.mudarSenha(senhaAntiga, novaSenha);
        repositorio.guardaUsuario(usuario);

    }

    public void editaEmailUsuario(String novoEmail, Usuario usuario){
        Repositorio repositorio = new Repositorio();
        usuario.mudarEmail(novoEmail);
        repositorio.guardaUsuario(usuario);

    }
}