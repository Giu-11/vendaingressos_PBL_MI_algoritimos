package telas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vendaingressos.Usuario;
import vendaingressos.Evento;
import vendaingressos.Controller;

import java.io.IOException;

public class EventoController {
    private Usuario usuarioLogado;
    private Evento evento;

    @FXML
    private Button home;
    @FXML
    private Button compra;
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
        this.ingressosDisponiveis.setText(this.evento.getTotalAssentos() - this.evento.getAssentosComprados() + " Ingressos disponíveis!!");
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
}
