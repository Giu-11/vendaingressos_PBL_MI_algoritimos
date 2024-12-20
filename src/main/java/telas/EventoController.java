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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import vendaingressos.Usuario;
import vendaingressos.Evento;
import vendaingressos.Controller;

import java.io.IOException;
import java.util.Map;

/**
 * controla a tela de evento
 */
public class EventoController {
    private Usuario usuarioLogado;
    private Evento evento;

    @FXML
    private Button home;
    @FXML
    private Button comprar;
    @FXML
    private Button comentar;
    @FXML
    private VBox comentarios;
    @FXML
    private Label nomeEvento;
    @FXML
    private Label ingressosDisponiveis;
    @FXML
    private Label preco;
    @FXML
    private Label data;
    @FXML
    private Label descricao;

    //construtor
    EventoController(Usuario usuario, Evento evento){
        this.evento = evento;
        this.usuarioLogado = usuario;
    }

    public void initialize(){
        this.nomeEvento.setText(this.evento.getNome());
        this.descricao.setText(this.evento.getDescricao());
        this.data.setText(this.evento.getDataformatada());
        this.preco.setText(this.evento.getPrecoIngresso().toString());

        this.colocaIngressosRestantes();

        this.atualizaBotoes();

        this.colocaComentarios();
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
     * se possível, abre a tela de compra e atualiza a quantidade de ingressos após ela ser fechada
     */
    @FXML
    private void comprarAction(){
        if(this.evento.isAtivo() && this.evento.getAssentosComprados()<this.evento.getTotalAssentos()) {
            try {
                CompraController compraController = new CompraController(this.evento, this.usuarioLogado);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/PopUpCompra.fxml"));
                loader.setController(compraController);
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Compra");
                stage.getIcons().add(new Image(getClass().getResource("/icons/carrinho.png").toExternalForm()));
                stage.setScene(new Scene(root));

                stage.setOnHidden(evnt -> {
                    this.colocaIngressosRestantes();
                    this.atualizaBotoes();});

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * se possível, abre a tela de comentar
     */
    @FXML
    private void comentarAction(){
        Controller controller = new Controller();
        if(!(this.evento.isAtivo() || !controller.usuarioPossuiIngresso(this.usuarioLogado, this.evento) || controller.usuarioJaComentou(this.evento, this.usuarioLogado))) {
            try {
                ComentarController comentarController = new ComentarController(this.evento, this.usuarioLogado);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/PopUpComentar.fxml"));
                loader.setController(comentarController);
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Comentar");
                stage.getIcons().add(new Image(getClass().getResource("/icons/comentario.png").toExternalForm()));
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    this.colocaComentarios();
                    this.atualizaBotoes();});

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * cria e coloca os comentários do evento
     */
    private void colocaComentarios(){
        for(Map.Entry<String, String> comentario: this.evento.getComentarios().entrySet()){
            String usuario = comentario.getKey();
            String textoComentario = comentario.getValue();
            try {
                ComantarioController comantarioController = new ComantarioController(usuario, textoComentario);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/comentario.fxml"));
                loader.setController(comantarioController);
                Pane cardComentario = loader.load();

                this.comentarios.getChildren().add(cardComentario);
                VBox.setVgrow(cardComentario, Priority.ALWAYS);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * mostra na tela quantos ingressos estão disponíveis
     */
    private void colocaIngressosRestantes(){
        if(this.evento.isAtivo()){
            this.ingressosDisponiveis.setText(this.evento.getTotalAssentos() - this.evento.getAssentosComprados() + " Ingressos disponíveis!!");
        } else {
            this.ingressosDisponiveis.setText("Esse evento já terminou");
        }
    }

    private void atualizaBotoes(){
        Controller controller = new Controller();
        if(!controller.usuarioPossuiIngresso(this.usuarioLogado, this.evento)){
            Tooltip tooltip = new Tooltip("Não é possível comentar em evento que você não possui um ingresso");
            Tooltip.install(this.comentar, tooltip);
            this.comentar.setStyle("-fx-background-color: #a291cf; -fx-background-radius: 20 ");
            this.comentar.setTextFill(Paint.valueOf("#EDCEE0"));
            //this.comentar.setDisable(true);
        } else if(controller.usuarioJaComentou(this.evento, this.usuarioLogado)){
            Tooltip tooltip = new Tooltip("Não é possível comentar em um evento mais de uma vez");
            Tooltip.install(this.comentar, tooltip);
            this.comentar.setStyle("-fx-background-color: #a291cf; -fx-background-radius: 20");
            this.comentar.setTextFill(Paint.valueOf("#EDCEE0"));
            //this.comentar.setDisable(true);
        }

        if(this.evento.isAtivo()){
            Tooltip tooltip = new Tooltip("Não é possível comentar em eventos que não aconteceram");
            Tooltip.install(this.comentar, tooltip);
            this.comentar.setStyle("-fx-background-color: #a291cf; -fx-background-radius: 20");
            this.comentar.setTextFill(Paint.valueOf("#EDCEE0"));
            //.comentar.setDisable(true);
        } else {
            Tooltip tooltip = new Tooltip("Não é possível comprar um evento que já passou");
            Tooltip.install(this.comprar, tooltip);
            this.comprar.setStyle("-fx-background-color: #a291cf; -fx-background-radius: 20");
            this.comprar.setTextFill(Paint.valueOf("#EDCEE0"));
            //this.comprar.setDisable(true);
        }
    }
}
