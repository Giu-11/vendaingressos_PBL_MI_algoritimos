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

import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

/**
 * Ingressos para um evento
 */
public class Ingresso {
    private String evento;
    private double preco;
    private String assento;
    private boolean ativo;
    private final String id;

    /**
     *
     * @param evento Evento para qual o ingresso é
     * @param preco Preço do ingresso
     * @param assento Assento do ingresso
     */
    public Ingresso( Evento evento, double preco, String assento) {
        this.evento = evento.getId();
        this.preco = preco;
        this.assento = assento;
        this.ativo = true;  //TODO colocar método p criar ele como n ativo
        this.id = UUID.fromString(evento.getNome()).toString();
    }

    //Getters
    public String getEvento() {
        return evento;
    }

    public String getId() {
        return id;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public String getAssento() {
        return this.assento;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingresso ingresso = (Ingresso) o;
        return Double.compare(preco, ingresso.preco) == 0 && ativo == ingresso.ativo && Objects.equals(evento, ingresso.evento) && Objects.equals(assento, ingresso.assento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evento, preco, assento, ativo);
    }

    /**
     * Cancela um evento
     * <p>
     *     Não é executada caso o evento tiver uma data anterior a atual, retornando falso
     * </p>
     * @return se a ação teve sucesso
     */
    public boolean cancelar() {
        //Data definida como 9 de setembro para simulação do código
        //garante que os resultados dos testes sejam os esperados para as datas definidas neles
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 9);

        if (calendar.getTime().before(this.evento.getData())) {

            /*FIXME Qual melhor forma de concertar isso?
                receber como parâmetro?
                colocar data no ingresso?
                ingresso procurar??
                pergunta disso na sessão*/

            this.ativo = false;
            return true;
        }
        return false;
    }

    //TODO tirar esse método?
    /**
     * Reativa o ingresso caso ele não seja de um evento anterior a data atual
     */
    public void reativar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 9);

        if (calendar.getTime().before(this.evento.getData())) {
            this.ativo = true;
        }
    }

}
