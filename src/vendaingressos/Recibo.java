package vendaingressos;

import java.util.Date;
import java.util.UUID;

public class Recibo {
    private final Date dataCompra;
    private final String idIngresso;
    private final String idUsuario;
    private final double valorPago;
    private final String formaPagamento;
    private final String id;

    public Recibo(Date dataCompra, String idIngresso, String idUsuario, double valorPago, String formaPagamento) {
        this.dataCompra = dataCompra;
        this.idIngresso = idIngresso;
        this.idUsuario = idUsuario;
        this.valorPago = valorPago;
        this.formaPagamento = formaPagamento;
        this.id = UUID.randomUUID().toString();
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public String getIdIngresso() {
        return idIngresso;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public double getValorPago() {
        return valorPago;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Recibo{" +
                "dataCompra=" + dataCompra +
                ", idIngresso='" + idIngresso + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", valorPago=" + valorPago +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
