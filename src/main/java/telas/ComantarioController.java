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
import javafx.scene.control.Label;

/**
 * controla um comentário
 */
public class ComantarioController {
    @FXML
    private Label usuario;
    @FXML
    private Label comentario;

    private final String textoComentario;
    private final String nomeUsuario;

    //construtor
    public ComantarioController(String nomeUsuario, String textoComentario) {
        this.nomeUsuario = nomeUsuario;
        this.textoComentario = textoComentario;
    }

    public void initialize(){
        this.usuario.setText(this.nomeUsuario);
        this.comentario.setText(this.textoComentario);
    }
}
