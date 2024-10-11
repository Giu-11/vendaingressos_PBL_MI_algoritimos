import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import vendaingressos.Evento;
import vendaingressos.Ingresso;
import vendaingressos.Usuario;
import vendaingressos.Repositorio;

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
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        Evento evento = new Evento("Show de Rock", "Banda XYZ", LocalDate.of(2024, Month.SEPTEMBER, 10), 10);
        Ingresso ingresso1 = new Ingresso(evento, 10.0, true, "Crédito");
        Ingresso ingresso2 = new Ingresso(evento, 10.0, true, "Débito");

        usuario.adicionarIngresso(ingresso1);
        usuario.adicionarIngresso(ingresso2);

        repositorio.guardaUsuario(usuario);

        Usuario usuario1 = repositorio.buscaUsuario("johndoe");

        assertEquals(usuario, usuario1);
    }

    @Test
    public void testAcessarUsuarioNaoExistente(){
        Repositorio repositorio = new Repositorio();
        Usuario usuario = repositorio.buscaUsuario("johndoe2");

        assertNull(usuario);
    }
}
