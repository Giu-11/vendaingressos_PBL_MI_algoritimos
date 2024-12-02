package telas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import vendaingressos.Controller;
import vendaingressos.Usuario;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button entrar;
    @FXML
    private TextField loginInput;
    @FXML
    private PasswordField senhaInput;
    @FXML
    private Button criarConta;

    @FXML
    private void entrarAction(){
        Controller controller = new Controller();
        String login = loginInput.getText();
        String senha = senhaInput.getText();
        if((login != null) && (senha != null)){
            Usuario usuario = controller.login(login, senha);
            if(usuario != null){
                System.out.println("foi vey");
                this.irTelaInicial(usuario);
            } else {
                this.avisoErroLoginSenha();
            }
        } else {
            this.avisoErroLoginSenha();
        }
    }

    @FXML
    private void criarContaAction(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/TelaCriarUsuario.fxml"));
            Parent root = loader.load();

            /*Stage stage = new Stage();
            stage.setTitle("Segunda Tela");
            stage.setScene(new Scene(root));
            stage.show();*/

            Stage stage = (Stage) criarConta.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void irTelaInicial(Usuario usuario){
        try{
            PrincipalController controllerTela = new PrincipalController(usuario);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/TelaPrincipal.fxml"));
            loader.setController(controllerTela);
            Parent root = loader.load();

            Stage stage = (Stage) this.criarConta.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void avisoErroLoginSenha(){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso!");
        alerta.setHeaderText("Login ou senha incorretos");
        alerta.show();
    }
}
