/*******************************************************************************************
 Autor: Giulia Aguiar Loula
 Componente Curricular: EXA863 - MI - PROGRAMAÇÃO
 Concluído em: 08/12/2024
 Declaro que este código foi elaborado por mim de forma individual e não contêm nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

 ********************************************************************************************/

package telas;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import vendaingressos.Controller;
import vendaingressos.Usuario;

/**
 * controla a tela de edição de senha
 */
public class EdicaoSenhaUsuarioController {
    @FXML
    private PasswordField senhaAtual;
    @FXML
    private PasswordField novaSenha;
    @FXML
    private Button confirmar;
    @FXML
    private Button cancelar;

    private final Usuario usuarioLogado;

    //construtor
    public EdicaoSenhaUsuarioController(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    /**
     * confirma se a senha atual está correta e caso esteja, muda para a nova senha
     */
    @FXML
    private void confirmarAction(){
        String senhaAtual = this.senhaAtual.getText();
        String senhaNova = this.novaSenha.getText();

        if(!(senhaNova.isEmpty() || senhaAtual.isEmpty())){
            Controller controller = new Controller();
            if(controller.editaSenhaUsuario(senhaNova, senhaAtual, usuarioLogado)){
                this.fechar();
            }else{
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Aviso!");
                alerta.setHeaderText("Senha atual incorreta");
                alerta.show();
            }
        }else{
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Aviso!");
            alerta.setHeaderText("Um espaço não foi preenchido!");
            alerta.setContentText("por favor preencha todos espaços");
            alerta.show();
        }
    }

    /**
     * fecha a janela
     */
    @FXML
    private void cancelarAction(){
        this.fechar();
    }

    /**
     * fecha a janela
     */
    private void fechar(){
        Stage stage = (Stage) this.cancelar.getScene().getWindow();
        stage.close();
    }
}
