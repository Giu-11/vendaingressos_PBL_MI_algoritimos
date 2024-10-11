package vendaingressos;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


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
