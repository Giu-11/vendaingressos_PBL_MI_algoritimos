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

    private Usuario usuarioLogado;

    public PrincipalController(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public void initialize(){
        Controller controller = new Controller();

        this.teste.setText(this.usuarioLogado.getNome());

        for(Ingresso ingresso: usuarioLogado.getIngressos()){
            try {
                CardController cardController = new CardController(ingresso);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/cardEvento.fxml"));
                loader.setController(cardController);
                Pane cardEvento = loader.load();

                this.eventos.getChildren().add(cardEvento);
                VBox.setVgrow(cardEvento, Priority.ALWAYS);

            } catch (IOException e) {
                e.printStackTrace();
            }
            /*
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("card.fxml"));
                Pane card = loader.load();

                CardController controller = loader.getController();
                controller.setData(item.getTitle(), item.getDescription());

                cardContainer.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
            */
        }
    }
}
