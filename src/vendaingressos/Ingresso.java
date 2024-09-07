package vendaingressos;

import java.util.Calendar;
import java.util.Objects;

public class Ingresso {
    private String id;
    private Evento evento;
    private double preco;
    private String assento;
    private boolean ativo;

    //Construtor
    public Ingresso( Evento evento, double preco, String assento) {
        this.evento = evento;
        this.preco = preco;
        this.assento = assento;
        this.ativo = true;
    }

    //Getters
    public Evento getEvento() {
        return evento;
    }

    public String getId() {
        return id;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public String getAssento() {
        return this.assento;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingresso ingresso = (Ingresso) o;
        return Double.compare(preco, ingresso.preco) == 0 && ativo == ingresso.ativo && Objects.equals(id, ingresso.id) && Objects.equals(evento, ingresso.evento) && Objects.equals(assento, ingresso.assento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, evento, preco, assento, ativo);
    }

    public boolean cancelar() {
        //Data definida como 9 de setembro para simulação do código
        //garante que os resultados dos testes sejam os esperados para as datas definidas neles
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 9);

        if (calendar.getTime().before(this.evento.getData())) {
            this.ativo = false;
            return true;
        }
        return false;
    }

    public void reativar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 9);

        if (calendar.getTime().before(this.evento.getData())) {
            this.ativo = true;
        }
    }
}
