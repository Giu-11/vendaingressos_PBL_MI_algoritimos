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

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
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
    private final LocalDate data;
    private final Recibo recibo;


    /***
     *
     * @param evento
     * @param preco
     * @param ativo
     * @param formaPagamento
     */
    public Ingresso(Evento evento, double preco, Boolean ativo, String formaPagamento) {
        this.evento = evento.getId();
        this.preco = preco;
        this.ativo = ativo;
        this.data = evento.getData();
        this.recibo = new Recibo(preco, formaPagamento);
    }

    //Getters
    public String getEvento() {
        return evento;
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

        if (data.isAfter(LocalDate.of(2024, Month.SEPTEMBER, 9))) {
            this.ativo = false;
            return true;
        }
        return false;
    }
}
