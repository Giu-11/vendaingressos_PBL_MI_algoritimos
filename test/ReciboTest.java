
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import vendaingressos.Recibo;

import java.time.LocalDate;
import java.time.Month;

public class ReciboTest {

    @Test
    public void testRecibo(){
        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 9);

        Recibo recibo = new Recibo(10.0, "PIX");

        assertEquals(data, recibo.getDataCompra());
        assertEquals("PIX", recibo.getFormaPagamento());
        assertEquals(10.0, recibo.getValorPago(), 0.0001);

        assertEquals("Recibo{dataCompra=09/09/2024, valorPago=10.0, formaPagamento=PIX}", recibo.toString());
    }
}
