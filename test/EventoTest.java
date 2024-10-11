
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import vendaingressos.Evento;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class EventoTest {

    @Test
    public void testCriarEvento() {
        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data, 100);

        assertNotNull(evento);
        assertEquals("Show de Rock", evento.getNome());
        assertEquals("Banda XYZ", evento.getDescricao());
        assertEquals(data, evento.getData());
    }


    @Test
    public void testEventoAtivo() {
        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data, 100);

        assertTrue(evento.isAtivo());
    }

    @Test
    public void testEventoInativo() {
        LocalDate data = LocalDate.of(2024, Month.JANUARY, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data, 100);

        assertFalse(evento.isAtivo());
    }
}
