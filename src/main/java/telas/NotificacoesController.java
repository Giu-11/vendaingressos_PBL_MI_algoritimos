package telas;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

import java.util.Objects;

public class NotificacoesController {

    @FXML
    private Label notificacao;
    @FXML
    private ImageView notificacaoIcone;

    private String texto;

    public NotificacoesController(String notificacao){
        this.texto = notificacao;
    }

    public void initialize(){
        if(!Character.isDigit(this.texto.charAt(0))){
            this.notificacao.setTextFill(Paint.valueOf("#8d0a41"));
            Image icone = new Image(Objects.requireNonNull(getClass().getResource("/icons/notificacaorosa.png")).toExternalForm());
            this.notificacaoIcone.setImage(icone);
        }
        this.notificacao.setText(this.texto);
    }
}
