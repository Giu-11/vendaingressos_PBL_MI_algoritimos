package telas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vendaingressos.Controller;
import vendaingressos.Usuario;

public class EdicaoSenhaUsuarioController {
    @FXML
    private TextField senhaAtual;
    @FXML
    private TextField novaSenha;
    @FXML
    private Button confirmar;
    @FXML
    private Button cancelar;

    private final Usuario usuarioLogado;

    public EdicaoSenhaUsuarioController(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    @FXML
    public void confirmarAction(){
        String senhaAtual = this.senhaAtual.getText();
        String senhaNova = this.novaSenha.getText();

        if(!(senhaNova.isEmpty() || senhaAtual.isEmpty())){
            Controller controller = new Controller();
            if(controller.editaSenhaUsuario(senhaNova, senhaAtual, usuarioLogado)){
                this.fechar();
            }
        }
    }

    @FXML
    public void cancelarAction(){
        this.fechar();
    }

    public void fechar(){
        Stage stage = (Stage) this.cancelar.getScene().getWindow();
        stage.close();
    }
}
