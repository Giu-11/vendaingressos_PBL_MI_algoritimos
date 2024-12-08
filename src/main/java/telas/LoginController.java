/*******************************************************************************************
 Autor: Giulia Aguiar Loula
 Componente Curricular: EXA863 - MI - PROGRAMAÇÃO
 Concluído em: 07/12/2024
 Declaro que este código foi elaborado por mim de forma individual e não contêm nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

 ********************************************************************************************/

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

/**
 * controla a tela de login
 */
public class LoginController {

    @FXML
    private Button entrar;
    @FXML
    private TextField loginInput;
    @FXML
    private PasswordField senhaInput;
    @FXML
    private Button criarConta;

    /**
     * Confirma se o login e senha estão corretos quando o botão de entrar é clicado
     */
    @FXML
    private void entrarAction(){
        Controller controller = new Controller();
        String login = loginInput.getText();
        String senha = senhaInput.getText();
        if((login != null) && (senha != null)){
            Usuario usuario = controller.login(login, senha);
            if(usuario != null){
                controller.notificacaoEventos(usuario);
                System.out.println("foi vey");
                this.irTelaInicial(usuario);
            } else {
                this.avisoErroLoginSenha();
            }
        } else {
            this.avisoErroLoginSenha();
        }
    }

    /**
     * abre a tela de criar conta
     */
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

    /**
     * abre a tela inicial
     * @param usuario usuário que fez login
     */
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

    /**
     * mostra um aviso caso o login e a senha estejam incorretos
     */
    private void avisoErroLoginSenha(){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso!");
        alerta.setHeaderText("Login ou senha incorretos");
        alerta.show();
    }
}
