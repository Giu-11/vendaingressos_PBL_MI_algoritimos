package vendaingressos;

public class Ingresso {
    private final String id;
    private final Evento evento;

    public Ingresso(String id, Evento evento) {
        this.id = id;
        this.evento = evento;
    }

    public Evento getEvento() {
        return evento;
    }

    public String getId() {
        return id;
    }

    public boolean isAtivo() {
        //consulta o evento e compara a data?
    }

    public boolean cancelar() {
    }

    public void reativar() {
    }
}
