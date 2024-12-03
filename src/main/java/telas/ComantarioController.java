package telas;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ComantarioController {
    @FXML
    private Label usuario;
    @FXML
    private Label comentario;

    private final String textoComentario;
    private final String nomeUsuario;

    public ComantarioController(String nomeUsuario, String textoComentario) {
        this.nomeUsuario = nomeUsuario;
        this.textoComentario = textoComentario;
    }

    public void initialize(){
        this.usuario.setText(this.nomeUsuario);
        this.comentario.setText(this.textoComentario);
    }
}
