package telas;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import vendaingressos.Controller;
import vendaingressos.Evento;
import vendaingressos.Usuario;

public class CompraController {
    @FXML
    private ChoiceBox<Integer> quantidade;
    @FXML
    private ChoiceBox<String> pagamento;
    @FXML
    private Button cancelar;
    @FXML
    private Button comprar;

    private final Usuario usuarioLogado;
    private final Evento evento;

    public CompraController(Evento evento, Usuario usuarioLogado) {
        this.evento = evento;
        this.usuarioLogado = usuarioLogado;
    }

    public void initialize(){
        quantidade.getItems().addAll(1, 2, 3, 4, 5);
        quantidade.setValue(1);

        pagamento.getItems().addAll("PIX", "Crédito", "Débito");
    }

    @FXML
    public void comprarAction(){
        String formaPagamento = this.pagamento.getValue();
        Integer quantidade = this.quantidade.getValue();
        if(formaPagamento != null && quantidade != null){
            if(!formaPagamento.trim().isEmpty()){
                Controller controller = new Controller();
                for(int i = 0; i<quantidade; i++){
                    controller.comprarIngresso(this.usuarioLogado, this.evento, formaPagamento);
                }
            } else {
                this.avisoNaoSelecionado();
            }
            this.cancelarAction();
        } else {
            this.avisoNaoSelecionado();
        }
    }

    @FXML
    public void cancelarAction(){
        Stage stage = (Stage) this.cancelar.getScene().getWindow();
        stage.close();
    }

    public void avisoNaoSelecionado(){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso!");
        alerta.setHeaderText("Pagamento ou quantidade não foi selecionado");
        alerta.setContentText("Por favor selecione um método de pagamento e uma quantidade de ingressos");
        alerta.show();
    }

}
