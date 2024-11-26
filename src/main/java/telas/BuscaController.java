package telas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vendaingressos.Controller;
import vendaingressos.Evento;
import vendaingressos.Usuario;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class BuscaController {

    private final Usuario usuarioLogado;

    @FXML
    private Button home;
    @FXML
    private Button buscar;
    @FXML
    private TextField nome;
    @FXML
    private CheckBox passados;
    @FXML
    private CheckBox futurosAntes;
    @FXML
    private VBox eventos;

    BuscaController(Usuario usuario){
        this.usuarioLogado = usuario;
    }

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

    @FXML
    private void buscarAction(){
        this.eventos.getChildren().clear();
        String nomeEvento = this.nome.getText();
        Boolean somenteFuturos = !this.passados.isSelected();
        Boolean futurosAntes = this.futurosAntes.isSelected();

        Controller controller = new Controller();

        List<Evento> eventos = controller.buscaEventoNome(nomeEvento, somenteFuturos);
        if(futurosAntes){
            Collections.reverse(eventos);
        }

        for(Evento evento: eventos){
            try {
                CardController cardController = new CardController(evento, usuarioLogado);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/cardEvento.fxml"));
                loader.setController(cardController);
                Pane cardEvento = loader.load();

                this.eventos.getChildren().add(cardEvento);
                VBox.setVgrow(cardEvento, Priority.ALWAYS);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
