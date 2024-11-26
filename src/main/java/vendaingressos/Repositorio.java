/*******************************************************************************************
 Autor: Giulia Aguiar Loula
 Componente Curricular: EXA863 - MI - PROGRAMAÇÃO
 Concluído em: 18/10/2024
 Declaro que este código foi elaborado por mim de forma individual e não contêm nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

 ********************************************************************************************/


package vendaingressos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;

import java.io.*;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por guardar e ler informações de arquivos
 */
public class Repositorio {

    /**
     *
     * @param usuario usuário que será guardado como arquivo
     */
    public void guardaUsuario(Usuario usuario){
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                .setPrettyPrinting()
                .create();
        try (FileWriter arquivo = new FileWriter("repositorio/Usuarios/"+usuario.getLogin()+".json")) {
            gson.toJson(usuario, arquivo);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param login login do usuário que deve ser buscado nos arquivos
     * @return usuário encontrado, ou null caso não exista
     */
    public Usuario buscaUsuario(String login){
        Usuario usuario = null;

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                .setPrettyPrinting()
                .create();

        File arquivo = new File("repositorio/Usuarios/"+login+".json");

        if(arquivo.exists()){
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                usuario = gson.fromJson(reader, Usuario.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }

    /**
     * @param evento evento que será guardado como arquivo
     */
    public void guardaEvento(Evento evento){
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                .setPrettyPrinting()
                .create();
        try (FileWriter arquivo = new FileWriter("repositorio/Eventos/"+evento.getId()+".json")) {
            gson.toJson(evento, arquivo);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id id do evento que deve ser buscado nos arquivos
     * @return evento encontrado, ou null coso ele não exista
     */
    public Evento buscaEventoId(String id){
        Evento evento = null;

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                .setPrettyPrinting()
                .create();

        File arquivo = new File("repositorio/Eventos/"+id+".json");

        if(arquivo.exists()){
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                evento = gson.fromJson(reader, Evento.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return evento;
    }

    /**
     * @param nome nome do evento que deve ser buscado nos arquivos
     * @return eventos encontrados
     */
    public List<Evento> buscaEventoNome(String nome, Boolean somenteFuturos){
        List<Evento> eventos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                .setPrettyPrinting()
                .create();

        Path caminho = Paths.get("repositorio/Eventos");

        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyyMMdd");
        String hoje = dataFormatada.format(LocalDate.now());

        try (DirectoryStream<Path> arquivos = Files.newDirectoryStream(caminho, "*.json")) {
            for (Path arquivo : arquivos) {

                String nomeArquivo = arquivo.getFileName().toString().toLowerCase();

                if(nomeArquivo.contains(nome.toLowerCase())){

                    if(somenteFuturos){
                        if(hoje.compareTo(arquivo.getFileName().toString()) < 0){
                            try (Reader reader = Files.newBufferedReader(arquivo)) {
                                eventos.add(gson.fromJson(reader, Evento.class));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }else {
                        try (Reader reader = Files.newBufferedReader(arquivo)) {
                            eventos.add(gson.fromJson(reader, Evento.class));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eventos;
    }


    /**
     * @return eventos encontrados que ocorrem depois da data atual
     */
    public List<Evento> listarEventosDisponiveis() {
        List<Evento> eventos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                .setPrettyPrinting()
                .create();

        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyyMMdd");
        String hoje = dataFormatada.format(LocalDate.now());

        Path caminho = Paths.get("repositorio/Eventos");

        try (DirectoryStream<Path> arquivos = Files.newDirectoryStream(caminho, "*.json")) {
            for (Path arquivo : arquivos) {
                if(hoje.compareTo(arquivo.getFileName().toString()) < 0){
                    try (Reader reader = Files.newBufferedReader(arquivo)) {
                        eventos.add(gson.fromJson(reader, Evento.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return eventos;
    }

    /**
     * @param login login do usuário
     * @return se o login fornecido já foi usado por outro usuário
     */
    public boolean usuarioExiste(String login){
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                .setPrettyPrinting()
                .create();

        File arquivo = new File("repositorio/Usuarios/"+login+".json");

        return arquivo.exists();
    }
}
