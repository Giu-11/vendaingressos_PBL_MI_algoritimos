package telas;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NotificacoesController {

    @FXML
    private Label notificacao;

    private String texto;

    public NotificacoesController(String notificacao){
        this.texto = notificacao;
    }

    public void initialize(){
        this.notificacao.setText(this.texto);
    }
}
