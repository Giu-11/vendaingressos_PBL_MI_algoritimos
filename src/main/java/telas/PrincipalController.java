package telas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import vendaingressos.Controller;
import vendaingressos.Ingresso;
import vendaingressos.Usuario;

import java.io.IOException;

public class PrincipalController {

    @FXML
    private Label teste;
    @FXML
    private VBox eventos;
    @FXML
    private VBox notificacoes;
    @FXML
    private Button busca;
    @FXML
    private Button usuario;

    private Usuario usuarioLogado;

    public PrincipalController(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void colocaEventos() {
        for (Ingresso ingresso : usuarioLogado.getIngressos()) {
            try {
                CardController cardController = new CardController(ingresso, usuarioLogado);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/cardEvento.fxml"));
                loader.setController(cardController);
                Pane cardEvento = loader.load();

                this.eventos.getChildren().add(cardEvento);
                VBox.setVgrow(cardEvento, Priority.ALWAYS);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void colocanotificacoes(){
        for (String notificacao : usuarioLogado.getNotificacoes()) {
            try {
                NotificacoesController notificacaoController = new NotificacoesController(notificacao);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/notificacao.fxml"));
                loader.setController(notificacaoController);
                Pane cardnotificacao = loader.load();

                this.notificacoes.getChildren().add(cardnotificacao);
                VBox.setVgrow(cardnotificacao, Priority.ALWAYS);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void initialize() {
        Controller controller = new Controller();

        this.teste.setText(this.usuarioLogado.getNome());

        this.colocaEventos();
        this.colocanotificacoes();
    }

    @FXML
    private void buscaAction(){
        try{
            BuscaController controllerTela = new BuscaController(usuarioLogado);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/TelaBusca.fxml"));
            loader.setController(controllerTela);
            Parent root = loader.load();

            Stage stage = (Stage) this.busca.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void usuarioAction(){
        try{
            UsuarioController usuarioController = new UsuarioController(usuarioLogado);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/TelaUsuario.fxml"));
            loader.setController(usuarioController);
            Parent root = loader.load();

            Stage stage = (Stage) this.usuario.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
