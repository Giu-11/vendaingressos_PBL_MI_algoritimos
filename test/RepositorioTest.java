import org.junit.After;
import org.junit.Test;
import org.testng.annotations.AfterMethod;
import vendaingressos.Evento;
import vendaingressos.Ingresso;
import vendaingressos.Usuario;
import vendaingressos.Repositorio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;

public class RepositorioTest {

    @Test
    public void testGuardaUsuario(){
        Repositorio repositorio = new Repositorio();
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        repositorio.guardaUsuario(usuario);
        Usuario usuario1 = repositorio.buscaUsuario("johndoe");

        assertEquals(usuario, usuario1);
    }

    @Test
    public void testGuardaUsuarioComIngressos(){
        Repositorio repositorio = new Repositorio();
        Usuario usuario = new Usuario("johndoe0", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        Evento evento = new Evento("Show de Rock", "Banda XYZ", LocalDate.of(2024, Month.SEPTEMBER, 10), 10);
        Ingresso ingresso1 = new Ingresso(evento,  true, "Crédito");
        Ingresso ingresso2 = new Ingresso(evento,  true, "Débito");

        usuario.adicionarIngresso(ingresso1);
        usuario.adicionarIngresso(ingresso2);

        repositorio.guardaUsuario(usuario);

        Usuario usuario1 = repositorio.buscaUsuario("johndoe0");

        assertEquals(usuario, usuario1);
    }

    @Test
    public void testAcessarUsuarioNaoExistente(){
        Repositorio repositorio = new Repositorio();
        Usuario usuario = repositorio.buscaUsuario("johndoe2");

        assertNull(usuario);
    }

    @Test
    public void testGuardaEvento(){
        Repositorio repositorio = new Repositorio();
        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock.2", "Banda ABC", data, 1000);

        String id = evento.getId();
        repositorio.guardaEvento(evento);
        Evento evento1 = repositorio.buscaEventoId(id);

        assertEquals(evento, evento1);
    }

    @Test
    public void testAcessarEventoNaoExistente(){
        Repositorio repositorio = new Repositorio();
        Evento evento = repositorio.buscaEventoId("idTesteEvento");

        assertNull(evento);
    }

    @Test
    public void testListarEventosFuturos(){
        Repositorio repositorio = new Repositorio();
        LocalDate data = LocalDate.of(2024, Month.SEPTEMBER, 10);
        Evento evento = new Evento("Festa", "festa", data, 100, 10.0);

        LocalDate data1 = LocalDate.of(2024, Month.SEPTEMBER, 11);
        Evento evento1 = new Evento("Festa2", "festa2", data1, 100, 10.0);

        LocalDate data2 = LocalDate.of(2023, Month.JANUARY, 8);
        Evento evento2 = new Evento("Festa3", "festa3", data2, 100, 10.0);

        repositorio.guardaEvento(evento);

        repositorio.guardaEvento(evento1);

        repositorio.guardaEvento(evento2);

        List<Evento> eventos = repositorio.listarEventosDisponiveis();

        assertEquals(2, eventos.size());
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
