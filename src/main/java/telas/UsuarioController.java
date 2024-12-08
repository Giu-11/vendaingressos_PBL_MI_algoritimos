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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import vendaingressos.Usuario;

import java.io.IOException;

/**
 * controla a tela de usuário
 */
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
    @FXML
    private Button mudarSenha;
    @FXML
    private Button mudarDados;

    private final Usuario usuarioLogado;


    //construtor
    public UsuarioController(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void initialize(){
        this.colocarDados();
    }


    /**
     * abre a tela de mudança de dados e atualiza a tela quando ela é fechada
     */
    @FXML
    private void mudarDadosAction(){
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

    /**
     * abre a tela de mudança de senha
     */
    @FXML
    private void mudarSenhaAction(){
        try {
            EdicaoSenhaUsuarioController edicaoSenhaUsuarioController = new EdicaoSenhaUsuarioController(this.usuarioLogado);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/edicaoSenhaUsuario.fxml"));
            loader.setController(edicaoSenhaUsuarioController);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Editar Dados");
            stage.getIcons().add(new Image(getClass().getResource("/icons/editar.png").toExternalForm()));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * retorna a tela inicial
     */
    @FXML
    private void homeAction(){
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

    /**
     * coloca os dados do usuário na tela
     */
    private void colocarDados(){
        this.nomeUsuario.setText(this.usuarioLogado.getNome());
        this.loginUsuario.setText(this.usuarioLogado.getLogin());
        this.emailUsuario.setText(this.usuarioLogado.getEmail());
        this.cpfUsuario.setText(this.usuarioLogado.getCpf());
    }
}
