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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import vendaingressos.Controller;
import vendaingressos.Usuario;

/**
 * controla a tela de criar usuário
 */
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

    /**
     * vai para a tela de login
     */
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

    /**
     * cria uma conta caso todos os espaços tenham sido preenchidos
     */
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

    /**
     * abre um aviso para quando os compos não foram preenchidos
     */
    private void avisoPreencherTudo(){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso!");
        alerta.setHeaderText("Campo(s) não preenchido(s)");
        alerta.setContentText("Por favor preencha todos campos");
        alerta.show();
    }
}
