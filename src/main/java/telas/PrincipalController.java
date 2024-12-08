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

/**
 * controla a tela inicial
 */
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

    //construtor
    public PrincipalController(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void initialize() {
        Controller controller = new Controller();

        this.teste.setText(this.usuarioLogado.getNome());

        this.colocaEventos();
        this.colocanotificacoes();
    }

    /**
     * Cria e coloca na lista os eventos do usuário
     */
    private void colocaEventos() {
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

    /**
     * Cria e coloca as notificações na lista
     */
    private void colocanotificacoes(){
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


    /**
     * Abre a tela de busca
     */
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

    /**
     * Abre a tela de dados do usuário
     */
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
