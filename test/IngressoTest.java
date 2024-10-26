
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import vendaingressos.Evento;
import vendaingressos.Ingresso;

public class IngressoTest {

    @Test
    public void testCriarIngresso() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 9);
        
        Evento evento = new Evento("Show de Rock", "Banda XYZ", data, 100, 100.0);
        Ingresso ingresso = new Ingresso(evento, true, "PIX");

        assertNotNull(ingresso);
        assertEquals(evento.getId(), ingresso.getEvento());
        assertEquals(100.0, ingresso.getPreco(), 0.0001);
        assertTrue(ingresso.isAtivo());
    }

    @Test
    public void testCancelarIngresso() {
        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data, 100);
        Ingresso ingresso = new Ingresso(evento, true, "PIX");

        assertTrue(ingresso.cancelar());
        assertFalse(ingresso.isAtivo());
    }

    @Test
    public void testCancelarIngressoEventoPassado() {
        LocalDate data = LocalDate.of(2024, Month.JANUARY, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data, 100);
        Ingresso ingresso = new Ingresso(evento, true, "PIX");

        assertFalse(ingresso.cancelar());
        assertTrue(ingresso.isAtivo());
    }

    @Test
    public void testReativarIngresso() {
        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data, 100);
        Ingresso ingresso = new Ingresso(evento, true, "PIX");

        ingresso.cancelar();
        assertFalse(ingresso.isAtivo());
    }

    @Test
    public void testIngressoDuplicado() {
        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data, 100);
        Ingresso ingresso1 = new Ingresso(evento,  true, "PIX");
        Ingresso ingresso2 = new Ingresso(evento,  true, "PIX");

        assertEquals(ingresso1, ingresso2);
        assertEquals(ingresso1.hashCode(), ingresso2.hashCode());
    }
}
