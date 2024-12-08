/*******************************************************************************************
 Autor: Giulia Aguiar Loula
 Componente Curricular: EXA863 - MI - PROGRAMAÇÃO
 Concluído em: 08/12/2024
 Declaro que este código foi elaborado por mim de forma individual e não contêm nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

 ********************************************************************************************/

package telas;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import vendaingressos.Controller;
import vendaingressos.Evento;
import vendaingressos.Usuario;

/**
 * controla a tela de comentar
 */
public class ComentarController {

    @FXML
    private TextArea comentarioTexto;
    @FXML
    private Button comentar;

    private final Usuario usuariologado;
    private final Evento evento;

    //construtor
    public ComentarController(Evento evento, Usuario usuariologado) {
        this.evento = evento;
        this.usuariologado = usuariologado;
    }

    /**
     * adiciona o comentário
     */
    @FXML
    private void comentarAction(){
        String comentario = this.comentarioTexto.getText();
        if(!comentario.isEmpty()) {
            Controller controller = new Controller();
            controller.adicionaComentario(this.evento, this.usuariologado, comentario);
            this.fechar();
        }else{
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Aviso!");
            alerta.setHeaderText("Por favor preencha todos campos");
            alerta.show();
        }
    }

    /**
     * fecha a janela
     */
    @FXML
    private void cancelarAction(){
        this.fechar();
    }

    /**
     * fecha a janela
     */
    private void fechar(){
        Stage stage = (Stage) this.comentar.getScene().getWindow();
        stage.close();
    }
}
