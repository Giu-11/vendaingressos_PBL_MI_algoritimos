import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import vendaingressos.Controller;
import vendaingressos.Evento;
import vendaingressos.Ingresso;
import vendaingressos.Usuario;

import static org.junit.Assert.*;

//NÃO RODE ESSES TESTES, ELES VÃO APAGAR OS ARQUIVOS/ NÃO DAR OS RESULTADOS ESPERADOS

public class ControllerTest {

    @Test
    public void testCadastrarEventoPorAdmin() {
        Controller controller = new Controller();
        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Evento evento = controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data, 1000, 0);

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
            controller.cadastrarEvento(usuario, "Peça de Teatro", "Grupo ABC", data, 100, 0);
        });

        assertEquals("Somente administradores podem cadastrar eventos.", exception.getMessage());
    }

    @Test
    public void testComprarIngresso() {
        Controller controller = new Controller();
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data, 20, 0);

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
        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data, 1000, 0);
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

        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data1, 100, 0);
        controller.cadastrarEvento(admin, "Peça de Teatro", "Grupo ABC", data2, 200, 0);

        List<Evento> eventos = controller.listarEventosDisponiveis();

        assertEquals(2, eventos.size());
    }

    @Test
    public void testListarIngressosComprados() {
        Controller controller = new Controller();
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data, 100, 0);
        controller.comprarIngresso(usuario, "Show de Rock", "PIX");

        List<Ingresso> ingressos = controller.listarIngressosComprados(usuario);

        assertEquals(1, ingressos.size());
    }

    @Test
    public void testEditarUsuario(){
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        controller.editaEmailUsuario("novo@email.com", usuario);
        controller.editaNomeUsuario("novonome", usuario);
        controller.editaSenhaUsuario("senha1234", "senha123", usuario);

        assertEquals("novo@email.com", usuario.getEmail());
        assertEquals("novonome", usuario.getNome());
        assertTrue(usuario.login("johndoe", "senha1234"));
        assertTrue(usuario == controller.login("johndoe", "senha1234"));
    }

    @Test
    public void testCriarUsuariosDuplicados(){
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        Usuario usuario2 = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        assertNotNull(usuario);
        assertNull(usuario2);
    }

    @After
    public void limpaArquivos(){
        Path caminho = Paths.get("repositorio/Usuarios/");


        try (DirectoryStream<Path> arquivos = Files.newDirectoryStream(caminho, "*.json")) {
            for (Path filePath : arquivos) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        caminho = Paths.get("repositorio/Eventos/");
        try (DirectoryStream<Path> arquivos = Files.newDirectoryStream(caminho, "*.json")) {
            for (Path filePath : arquivos) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}