
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import vendaingressos.Recibo;

import java.util.Calendar;
import java.util.Date;

public class ReciboTest {

    @Test
    public void testRecibo(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();
        Recibo recibo = new Recibo(data, "idIngresso", "idUsuario", 10.0, "PIX");
        assertEquals(calendar.getTime(), recibo.getDataCompra());
        assertEquals("PIX", recibo.getFormaPagamento());
        assertEquals(10.0, recibo.getValorPago(), 0.0001);
        assertEquals("idIngresso",recibo.getIdIngresso());
        assertEquals("idUsuario", recibo.getIdUsuario());
    }
}
