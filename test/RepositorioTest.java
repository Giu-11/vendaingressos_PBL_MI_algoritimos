import org.junit.After;
import org.junit.Test;
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

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data, 1000);

        String id = evento.getId();
        repositorio.guardaEvento(evento);
        Evento evento1 = repositorio.buscaEvento(id);

        assertEquals(evento, evento1);
    }

    @Test
    public void testAcessarEventoNaoExistente(){
        Repositorio repositorio = new Repositorio();
        Evento evento = repositorio.buscaEvento("idTesteEvento");

        assertNull(evento);
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

    //TODO teste com evento passado (10 de jan)(p a busca de eventos futuros)
}
