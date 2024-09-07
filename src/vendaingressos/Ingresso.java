package vendaingressos;

public class Ingresso {
    private String id;
    private Evento evento;
    private double preco;

    public Ingresso(Evento evento, double preco, String a1) {
    }


    public Evento getEvento() {
        return evento;
    }

    public String getId() {
        return id;
    }

    public boolean isAtivo() {
        //consulta o evento e compara a data?
        return false;
    }

    public boolean cancelar() {
        return false;
    }

    public void reativar() {
        return;
    }

    public String getAssento() {
        return "";
    }

    public double getPreco() {
        return preco;
    }
}
