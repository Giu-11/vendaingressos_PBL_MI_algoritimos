/*******************************************************************************************
 Autor: Giulia Aguiar Loula
 Componente Curricular: EXA863 - MI - PROGRAMAÇÃO
 Concluído em: 07/12/2024
 Declaro que este código foi elaborado por mim de forma individual e não contêm nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

 ********************************************************************************************/

package vendaingressos;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

/**
 * Ingressos para um evento
 */
public class Ingresso {
    private String evento;
    private double preco;
    private boolean ativo;
    private final LocalDate data;
    private final Recibo recibo;
    private final String dataformatada;
    private final String nomeEvento;


    /**
     *
     * @param evento
     * @param ativo
     * @param formaPagamento
     */
    public Ingresso(Evento evento, Boolean ativo, String formaPagamento) {
        this.evento = evento.getId();
        this.preco = evento.getPrecoIngresso();
        this.ativo = ativo;
        this.data = evento.getData();
        this.recibo = new Recibo(evento.getPrecoIngresso(), formaPagamento);
        this.dataformatada = evento.getDataformatada();
        this.nomeEvento = evento.getNome();
    }

    //Getters
    public String getEvento() {
        return evento;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public double getPreco() {
        return preco;
    }

    public Recibo getRecibo() {
        return recibo;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDataformatada() {
        return dataformatada;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingresso ingresso = (Ingresso) o;
        return Double.compare(preco, ingresso.preco) == 0 && ativo == ingresso.ativo && Objects.equals(evento, ingresso.evento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evento, preco, ativo);
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

        if (data.isAfter(LocalDate.now())) {
            this.ativo = false;
            return true;
        }
        return false;
    }
}
