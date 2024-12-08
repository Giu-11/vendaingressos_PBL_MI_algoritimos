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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

import java.util.Objects;

/**
 * Controla uma notificação
 */
public class NotificacoesController {

    @FXML
    private Label notificacao;
    @FXML
    private ImageView notificacaoIcone;

    private String texto;

    //construtor
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
