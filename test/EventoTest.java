
import java.time.LocalDate;
import java.time.Month;

import vendaingressos.Evento;

import org.junit.Test;
import vendaingressos.Ingresso;
import vendaingressos.Usuario;

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

    @Test
    public void testCompraIngressos(){
        LocalDate data = LocalDate.of(2024, Month.JANUARY, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data, 2);

        evento.compraIngresso();
        evento.compraIngresso();
        evento.compraIngresso();

        assertEquals(2, evento.getAssentosComprados());
        evento.cancelaCompra();
        assertEquals(1, evento.getAssentosComprados());
    }

    @Test
    public void testComentarioSemIngresso(){
        Usuario usuario = new Usuario("usuario123", "abc123", "Maria Silva da Silva", "123", "mariass@email.com", false);
        Evento evento = new Evento("Meditação", "vamos olhar tinta secar", LocalDate.of(2023, Month.JANUARY, 9), 10, 0.0);

        evento.adicionaComentario(usuario, "não gostei");

        assertEquals(0, evento.getComentarios().size());
    }

    @Test
    public void testComentarioFuturo(){
        Usuario usuario = new Usuario("usuario123", "abc123", "Maria Silva da Silva", "123", "mariass@email.com", false);
        Evento evento = new Evento("Meditação", "vamos olhar tinta secar", LocalDate.of(2024, Month.SEPTEMBER, 10), 10, 0.0);

        Ingresso ingresso = new Ingresso(evento, true, "NONE");

        usuario.adicionarIngresso(ingresso);

        evento.adicionaComentario(usuario, "entediante");

        assertEquals(0, evento.getComentarios().size());
    }

    @Test
    public void testComentarioPassado(){
        Usuario usuario = new Usuario("usuario123", "abc123", "Maria Silva da Silva", "123", "mariass@email.com", false);
        Evento evento = new Evento("Meditação", "vamos olhar tinta secar", LocalDate.of(2023, Month.JANUARY, 9), 10, 0.0);

        Ingresso ingresso = new Ingresso(evento, true, "NONE");

        usuario.adicionarIngresso(ingresso);

        evento.adicionaComentario(usuario, "entediante");

        evento.adicionaComentario(usuario, "não gostei");

        assertEquals(1, evento.getComentarios().size());
    }
}
