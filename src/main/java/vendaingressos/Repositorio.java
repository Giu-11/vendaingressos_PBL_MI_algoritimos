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

public class Repositorio {

    public void guardaUsuario(Usuario usuario){
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                .setPrettyPrinting()
                .create();
        try (FileWriter arquivo = new FileWriter("repositorio/"+usuario.getLogin()+".json")) {
            gson.toJson(usuario, arquivo);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Usuario buscaUsuario(String login){
        Usuario usuario = null;
        /*
        Path caminho = Paths.get("repositorio/");

        try (DirectoryStream<Path> arquivos = Files.newDirectoryStream(caminho, "*.json")) {
            for (Path filePath : arquivos) {
                System.out.println("Arquivo JSON: " + filePath.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                .setPrettyPrinting()
                .create();

        File arquivo = new File("repositorio/"+login+".json");

        if(arquivo.exists()){
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                usuario = gson.fromJson(reader, Usuario.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }


}
