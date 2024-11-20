package telas;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import vendaingressos.Evento;
import vendaingressos.Ingresso;

public class CardController {

    @FXML
    private Button nomeEvento;
    @FXML
    private Label dataEvento;

    private String nome;
    private String data;

    CardController(Ingresso ingresso){
        this.nome = ingresso.getEvento();
        this.data = ingresso.getDataformatada();
    }

    public void initialize(){
        this.nomeEvento.setText(this.nome);
        this.dataEvento.setText(this.data);
    }

    @FXML
    private void nomeEventoAction(){
        //vai para a tela do evento
    }
}
