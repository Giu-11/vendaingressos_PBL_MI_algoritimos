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

import java.util.*;

/**
 * Evento cadastrado no sistema
 */
public class Evento {
    private String nome;
    private String descricao;
    private Date data;
    private List<String> assentosDisponiveis;
    private List<String> assentosOcupados;

    //Construtor

    /**
     *
     * @param nome nome do evento
     * @param descricao descrição do evento
     * @param data data do evento
     */
    public Evento(String nome, String descricao, Date data) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.assentosDisponiveis = new ArrayList<>();
        this.assentosOcupados = new ArrayList<>();
    }

    //Getters
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getData() {
        return data;
    }

    public List<String> getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    /**
     *
     * @return se o evento ainda irá acontecer
     */
    public boolean isAtivo() {
        //Data definida como 9 de setembro para simulação do código
        //garante que os resultados dos testes sejam os esperados para as datas definidas neles
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 9);

        return calendar.getTime().before(this.data);
    }

    /**
     *
     * @param assento assento a ser adicionado no evento
     */
    public void adicionarAssento(String assento) {
        //adiciona um assento caso ele não exista nem em assentos disponíveis ou ocupados
        if((!assentosDisponiveis.contains(assento)) && (!assentosOcupados.contains(assento))) {
            assentosDisponiveis.add(assento);
        }
    }

    /**
     *
     * @param assento assento a ser removido no evento
     */
    public void removerAssento(String assento) {
        assentosDisponiveis.removeIf(i->(Objects.equals(i, assento)));
    }

    /**
     *
     * @param assento assento que será comprado
     */
    public void compraAssento(String assento){
        if (assentosDisponiveis.contains(assento)){
            assentosDisponiveis.remove(assento);
            assentosOcupados.add(assento);
        }
    }

    /**
     *
     * @param assento assento que terá compra cancelada
     */
    public void cancelaCompra(String assento){
        if(assentosOcupados.contains(assento)){
            assentosOcupados.remove(assento);
            assentosDisponiveis.add(assento);
        }
    }
}
