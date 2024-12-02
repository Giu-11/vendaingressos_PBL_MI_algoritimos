package telas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import vendaingressos.Controller;
import vendaingressos.Usuario;

public class CriarUsuarioController {
    @FXML
    private Button contaExistente;
    @FXML
    private TextField loginInput;
    @FXML
    private TextField senhaInput;
    @FXML
    private TextField nomeImput;
    @FXML
    private TextField cpfImput;
    @FXML
    private TextField emailImput;

    @FXML
    private void contaExistenteAction(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/TelaLogin.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) contaExistente.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void criarAction(){
        String login = loginInput.getText();
        String senha = senhaInput.getText();
        String cpf = cpfImput.getText();
        String nome = nomeImput.getText();
        String email = emailImput.getText();

        if(!login.isEmpty() && !senha.isEmpty() && !cpf.isEmpty() && !nome.isEmpty() && !email.isEmpty()){
            Controller controller =  new Controller();

            Usuario usuario = controller.cadastrarUsuario(login, senha, nome, cpf, email, false);

            if(usuario != null){
                System.out.println("criado");
                contaExistenteAction();
            }else{
                System.out.println("não");
            }
        } else {
            this.avisoPreencherTudo();
        }
    }

    public void avisoPreencherTudo(){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso!");
        alerta.setHeaderText("Campo(s) não preenchido(s)");
        alerta.setContentText("Por favor preencha todos campos");
        alerta.show();
    }
}
