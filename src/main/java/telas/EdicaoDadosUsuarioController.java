package telas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vendaingressos.Controller;
import vendaingressos.Usuario;

public class EdicaoDadosUsuarioController {
    @FXML
    private TextField novoNome;
    @FXML
    private TextField novoCPF;
    @FXML
    private TextField novoEmail;
    @FXML
    private Button confirmar;
    @FXML
    private Button cancelar;

    private final Usuario usuarioLogado;

    public EdicaoDadosUsuarioController(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    @FXML
    public void confirmarAction(){
        Controller controller = new Controller();
        String nome = this.novoNome.getText();
        if(!nome.isEmpty()){
            controller.editaNomeUsuario(nome, this.usuarioLogado);
        }
        String cpf = this.novoCPF.getText();
        if(!cpf.isEmpty()){
            controller.editaCpfUsuario(cpf, this.usuarioLogado);
        }
        String email = this.novoEmail.getText();
        if(!email.isEmpty()){
            controller.editaEmailUsuario(email, this.usuarioLogado);
        }

        this.fechar();
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
