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
