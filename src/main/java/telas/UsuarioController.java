package telas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import vendaingressos.Usuario;

import java.io.IOException;

public class UsuarioController {

    @FXML
    private Label nomeUsuario;
    @FXML
    private Label loginUsuario;
    @FXML
    private Label emailUsuario;
    @FXML
    private Label cpfUsuario;
    @FXML
    private Button home;

    private final Usuario usuarioLogado;


    public UsuarioController(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void initialize(){
        this.colocarDados();
    }

    @FXML
    public void mudarDadosAction(){
        try {
            EdicaoDadosUsuarioController edicaoDadosUsuarioController = new EdicaoDadosUsuarioController(this.usuarioLogado);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/edicaoDadosUsuario.fxml"));
            loader.setController(edicaoDadosUsuarioController);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Editar Dados");
            stage.getIcons().add(new Image(getClass().getResource("/icons/editar.png").toExternalForm()));
            stage.setScene(new Scene(root));

            stage.setOnHidden(evnt -> {this.colocarDados();});

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mudarSenhaAction(){
        //TODO
    }

    @FXML
    public void homeAction(){
        try{
            PrincipalController controllerTela = new PrincipalController(usuarioLogado);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/TelaPrincipal.fxml"));
            loader.setController(controllerTela);
            Parent root = loader.load();

            Stage stage = (Stage) this.home.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void colocarDados(){
        this.nomeUsuario.setText(this.usuarioLogado.getNome());
        this.loginUsuario.setText(this.usuarioLogado.getLogin());
        this.emailUsuario.setText(this.usuarioLogado.getEmail());
        this.cpfUsuario.setText(this.usuarioLogado.getCpf());
    }
}
