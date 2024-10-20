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
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * Recibo de uma compra de um ingresso
 */
public class Recibo {
    private final LocalDate dataCompra;
    private final double valorPago;
    private final String formaPagamento;

    public Recibo( double valorPago, String formaPagamento) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 9);

        this.dataCompra = LocalDate.of(2024, Month.SEPTEMBER, 9);
        this.valorPago = valorPago;
        this.formaPagamento = formaPagamento;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public double getValorPago() {
        return valorPago;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    @Override
    public String toString() {
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = dataFormatada.format(this.dataCompra);

        return "Recibo{" +
                "dataCompra=" + dataString +
                ", valorPago=" + valorPago +
                ", formaPagamento=" + formaPagamento +
                '}';
    }
}
