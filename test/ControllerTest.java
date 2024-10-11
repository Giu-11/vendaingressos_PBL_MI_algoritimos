import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import vendaingressos.Controller;
import vendaingressos.Evento;
import vendaingressos.Ingresso;
import vendaingressos.Usuario;


public class ControllerTest {

    @Test
    public void testCadastrarEventoPorAdmin() {
        Controller controller = new Controller();
        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Evento evento = controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data, 1000);

        assertNotNull(evento);
        assertEquals("Show de Rock", evento.getNome());
        assertEquals("Banda XYZ", evento.getDescricao());
        assertEquals(data, evento.getData());
    }

    @Test
    public void testCadastrarEventoPorUsuarioComum() {
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Exception exception = assertThrows(SecurityException.class, () -> {
            controller.cadastrarEvento(usuario, "Peça de Teatro", "Grupo ABC", data, 100);
        });

        assertEquals("Somente administradores podem cadastrar eventos.", exception.getMessage());
    }

    @Test
    public void testComprarIngresso() {
        Controller controller = new Controller();
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data, 20);

        Ingresso ingresso = controller.comprarIngresso(usuario, "Show de Rock", "PIX");

        assertNotNull(ingresso);
        assertEquals("Show de Rock", controller.buscaEvento(ingresso.getEvento()).getNome());
        assertTrue(usuario.getIngressos().contains(ingresso));
    }

    @Test
    public void testCancelarCompra() {
        Controller controller = new Controller();
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data, 1000);
        Ingresso ingresso = controller.comprarIngresso(usuario, "Show de Rock", "Crédito");

        boolean cancelado = controller.cancelarCompra(usuario, ingresso);
        assertTrue(cancelado);
        assertFalse(ingresso.isAtivo());
        assertFalse(usuario.getIngressos().contains(ingresso));
    }

    @Test
    public void testListarEventosDisponiveis() {
        Controller controller = new Controller();
        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        LocalDate data1 = LocalDate.of(2024, Month.SEPTEMBER, 10);

        LocalDate data2 = LocalDate.of(2024, Month.SEPTEMBER, 15);

        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data1, 100);
        controller.cadastrarEvento(admin, "Peça de Teatro", "Grupo ABC", data2, 200);

        List<Evento> eventos = controller.listarEventosDisponiveis();

        assertEquals(2, eventos.size());
    }

    @Test
    public void testListarIngressosComprados() {
        Controller controller = new Controller();
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data, 100);
        controller.comprarIngresso(usuario, "Show de Rock", "PIX");

        List<Ingresso> ingressos = controller.listarIngressosComprados(usuario);

        assertEquals(1, ingressos.size());
    }
}