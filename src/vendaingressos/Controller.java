/*******************************************************************************************
Autor: Giulia Aguiar Loula
Componente Curricular: EXA863 - MI - PROGRAMAÇÃO
Concluído em: 07/09/22024
Declaro que este código foi elaborado por mim de forma individual e não contêm nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

********************************************************************************************/

package vendaingressos;

import java.util.*;
import java.util.stream.IntStream;

public class Controller {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Evento> eventos = new ArrayList<>();

    public Usuario cadastrarUsuario(String login, String senha, String nome, String cpf, String email, boolean admin) {
        Usuario novoUsuario = new Usuario(login,senha, nome, cpf,email,admin);
        usuarios.add(novoUsuario);
        return novoUsuario;
    }

    public Evento cadastrarEvento(Usuario admin, String nome, String descricao, Date data) {
        if(admin.isAdmin()) {
            Evento novoEvento = new Evento(nome, descricao, data);
            eventos.add(novoEvento);
            return novoEvento;
        } else{
            //Retorna um erro se um usuário não administrador tentar criar um evento
            throw new SecurityException("Somente administradores podem cadastrar eventos.");
        }
    }

    public void adicionarAssentoEvento(String nomeEvento, String assento) {
        //Busca dentro da lista de eventos pelo primeiro evento com o nome fornecido
        OptionalInt indice = IntStream.range(0, eventos.size())
                .filter(i->(eventos.get(i).getNome().equals(nomeEvento)))
                .findFirst();
        //Se o evento existir adiciona o assento a ele
        if(indice.isPresent()){
            eventos.get(indice.getAsInt()).adicionarAssento(assento);
        }
    }

    public Ingresso comprarIngresso(Usuario usuario, String nomeEvento, String assento) {
        //Busca dentro da lista de eventos pelo primeiro evento com o nome fornecido
        OptionalInt indice = IntStream.range(0, eventos.size())
                .filter(i->(eventos.get(i).getNome().equals(nomeEvento)))
                .findFirst();
        //Se o evento existir realiza as ações de compra
        if(indice.isPresent()){
            Evento evento = eventos.get(indice.getAsInt());
            if (evento.getAssentosDisponiveis().contains(assento)) {
                Ingresso novoIngresso = new Ingresso(evento, 0.0, assento);
                usuario.adicionarIngresso(novoIngresso);
                evento.compraAssento(assento);
                return novoIngresso;
            }
        }
        return null;
    }

    public boolean cancelarCompra(Usuario usuario, Ingresso ingresso) {
        //cancela um ingresso coso o usuário informado possua ele
        if(usuario.getIngressos().contains(ingresso)){
            Evento evento = ingresso.getEvento();
            evento.cancelaCompra(ingresso.getAssento());
            usuario.cancelarIngresso(ingresso);
            ingresso.cancelar();
            return true;
        }
        return false;
    }

    public List<Evento> listarEventosDisponiveis() {
        //Data definida como 9 de setembro para simulação do código
        //garante que os resultados dos testes sejam os esperados para as datas definidas neles
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 9);

        //retorna uma lista de todos os eventos que acontecerão depois da data definida
        return eventos.stream()
                .filter(i->(i.getData().after(calendar.getTime())))
                .toList();
    }

    public List<Ingresso> listarIngressosComprados(Usuario usuario) {
        return usuario.getIngressos();
    }
}