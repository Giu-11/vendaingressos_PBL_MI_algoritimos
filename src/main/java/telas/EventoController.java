package telas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vendaingressos.Usuario;
import vendaingressos.Evento;
import vendaingressos.Controller;

import java.io.IOException;
import java.util.Map;

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

    EventoController(Usuario usuario, Evento evento){
        this.evento = evento;
        this.usuarioLogado = usuario;
    }

    public void initialize(){
        this.nomeEvento.setText(this.evento.getNome());
        this.descricao.setText(this.evento.getDescricao());
        this.data.setText(this.evento.getDataformatada());
        this.preco.setText(this.evento.getPrecoIngresso().toString());

        if(this.evento.isAtivo()){
            this.ingressosDisponiveis.setText(this.evento.getTotalAssentos() - this.evento.getAssentosComprados() + " Ingressos disponíveis!!");
        } else {
            this.ingressosDisponiveis.setText("Esse evento já terminou");
        }

        if(this.evento.isAtivo()){
            this.comentar.setDisable(true);
        } else {
            this.comprar.setDisable(true);
        }
        Controller controller = new Controller();
        if(!controller.usuarioPossuiIngresso(this.usuarioLogado, this.evento) || controller.usuarioJaComentou(this.evento, this.usuarioLogado)){
            this.comentar.setDisable(true);
        }

        this.colocaComentarios();
    }

    @FXML
    public void homeAction(){
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

    @FXML
    public void comprarAction(){
        try {
            CompraController compraController = new CompraController(this.evento, this.usuarioLogado);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/PopUpCompra.fxml"));
            loader.setController(compraController);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Compra");
            stage.getIcons().add(new Image(getClass().getResource("/icons/carrinho.png").toExternalForm()));
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void comentarAction(){
        try {
            ComentarController comentarController = new ComentarController(this.evento, this.usuarioLogado);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/PopUpComentar.fxml"));
            loader.setController(comentarController);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Comentar");
            stage.getIcons().add(new Image(getClass().getResource("/icons/comentario.png").toExternalForm()));
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void colocaComentarios(){
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
}
