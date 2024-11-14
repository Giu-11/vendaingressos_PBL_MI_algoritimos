package telas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class Telas extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/TelaLogin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setTitle("Venda Ingressos!");
            stage.getIcons().add(new Image(getClass().getResource("/icons/loja.png").toExternalForm()));
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
}
