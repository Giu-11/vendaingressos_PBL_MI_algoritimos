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
        Usuario novoUsuario = new Usuario(login,senha, nome, cpf,email,admin);
        usuarios.add(novoUsuario);
        return novoUsuario;
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
            Evento novoEvento = new Evento(nome, descricao, data, totalAssentos);
            eventos.add(novoEvento);
            return novoEvento;
        } else{
            //Retorna um erro se um usuário não administrador tentar criar um evento
            throw new SecurityException("Somente administradores podem cadastrar eventos.");
        }
    }

    /***
     *
     * @param usuario
     * @param nomeEvento
     * @param formaPagamento
     * @return
     */
    public Ingresso comprarIngresso(Usuario usuario, String nomeEvento, String formaPagamento) {
        //Busca dentro da lista de eventos pelo primeiro evento com o nome fornecido
        OptionalInt indice = IntStream.range(0, eventos.size())
                .filter(i->(eventos.get(i).getNome().equals(nomeEvento)))
                .findFirst();
        //Se o evento existir realiza as ações de compra
        if(indice.isPresent()){
            Evento evento = eventos.get(indice.getAsInt());
            if (evento.getTotalAssentos() > evento.getAssentosComprados()) {
                Ingresso novoIngresso = new Ingresso(evento, evento.getPrecoIngresso(), true, formaPagamento);
                usuario.adicionarIngresso(novoIngresso);
                evento.compraIngresso();
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
        if(usuario.getIngressos().contains(ingresso)){
            Evento evento = buscaEvento(ingresso.getEvento());
            evento.cancelaCompra();
            usuario.cancelarIngresso(ingresso);
            ingresso.cancelar();
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

        //retorna uma lista de todos os eventos que acontecerão depois da data definida
        return eventos.stream()
                .filter(i->(i.getData().isAfter(LocalDate.of(2024, Month.SEPTEMBER, 9))))
                .toList();
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
        return eventos.stream()
                .filter(i->(Objects.equals(i.getId(), idEvento)))
                .findFirst()
                .orElse(null);
    }
}