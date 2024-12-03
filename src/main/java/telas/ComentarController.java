package telas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import vendaingressos.Controller;
import vendaingressos.Evento;
import vendaingressos.Usuario;

public class ComentarController {

    @FXML
    private TextArea comentarioTexto;
    @FXML
    private Button comentar;

    private final Usuario usuariologado;
    private final Evento evento;

    public ComentarController(Evento evento, Usuario usuariologado) {
        this.evento = evento;
        this.usuariologado = usuariologado;
    }

    @FXML
    public void comentarAction(){
        String comentario = this.comentarioTexto.getText();
        Controller controller = new Controller();
        controller.adicionaComentario(this.evento, this.usuariologado, comentario);
        Stage stage = (Stage) this.comentar.getScene().getWindow();
        stage.close();
    }
}
