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

public class Repositorio {

    /**
     *
     * @param usuario
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
     * @param login
     * @return
     */
    public Usuario buscaUsuario(String login){
        /*
        Path caminho = Paths.get("repositorio/");

        try (DirectoryStream<Path> arquivos = Files.newDirectoryStream(caminho, "*.json")) {
            for (Path filePath : arquivos) {
                System.out.println("Arquivo JSON: " + filePath.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
     *
     * @param evento
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
     *
     * @param id
     * @return
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

    public List<Evento> buscaEventoNome(String nome){
        List<Evento> eventos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                .setPrettyPrinting()
                .create();

        Path caminho = Paths.get("repositorio/Eventos");

        try (DirectoryStream<Path> arquivos = Files.newDirectoryStream(caminho, "*.json")) {
            for (Path arquivo : arquivos) {
                if(arquivo.getFileName().toString().toLowerCase().contains(nome.toLowerCase())){
                    try (Reader reader = Files.newBufferedReader(arquivo)) {
                        eventos.add(gson.fromJson(reader, Evento.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Arquivo JSON: " + arquivo.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eventos;
    }


    public List<Evento> listarEventosDisponiveis() {
        List<Evento> eventos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                .setPrettyPrinting()
                .create();

        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyyMMdd");
        String hoje = dataFormatada.format(LocalDate.of(2024, Month.SEPTEMBER, 9));

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
                System.out.println("Arquivo JSON: " + arquivo.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return eventos;
    }
}
