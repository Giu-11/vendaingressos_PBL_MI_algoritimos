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

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;
import vendaingressos.Evento;
import vendaingressos.Usuario;
import vendaingressos.Ingresso;
import vendaingressos.Controller;


import java.io.IOException;

/**
 * controla um card de um evento
 */
public class CardController {

    @FXML
    private Button nomeEvento;
    @FXML
    private Label dataEvento;

    private String nome;
    private String data;
    private String idEvento;
    private Usuario usuariologado;

    //construtor usando ingresso
    CardController(Ingresso ingresso, Usuario usuario){
        this.nome = ingresso.getNomeEvento();
        this.data = ingresso.getDataformatada();
        this.idEvento = ingresso.getEvento();
        this.usuariologado = usuario;
    }

    //construtor usando evento
    CardController(Evento evento, Usuario usuario){
        this.nome = evento.getNome();
        this.data = evento.getDataformatada();
        this.idEvento = evento.getId();
        this.usuariologado = usuario;
    }

    public void initialize(){
        this.nomeEvento.setText(this.nome);
        this.dataEvento.setText(this.data);
    }

    //abre a tela do evento
    @FXML
    private void nomeEventoAction(){
        try{
            Controller controller = new Controller();
            Evento evento = controller.buscaEvento(idEvento);
            EventoController controllerTela = new EventoController(usuariologado, evento);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/TelaEvento.fxml"));
            loader.setController(controllerTela);
            Parent root = loader.load();

            Stage stage = (Stage) this.nomeEvento.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
