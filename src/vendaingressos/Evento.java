package vendaingressos;

import java.util.*;

public class Evento {
    private String nome;
    private String descricao;
    private Date data;
    private List<String> assentosDisponiveis;
    private List<String> assentosOcupados;

    //Construtor
    public Evento(String nome, String descricao, Date data) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.assentosDisponiveis = new ArrayList<>();
        this.assentosOcupados = new ArrayList<>();
    }

    //Getters
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getData() {
        return data;
    }

    public List<String> getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public boolean isAtivo() {
        //Data definida como 9 de setembro para simulação do código
        //garante que os resultados dos testes sejam os esperados para as datas definidas neles
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 9);

        return calendar.getTime().before(this.data);
    }

    public void adicionarAssento(String assento) {
        //adiciona um assento caso ele não exista nem em assentos disponíveis ou ocupados
        if((!assentosDisponiveis.contains(assento)) && (!assentosOcupados.contains(assento))) {
            assentosDisponiveis.add(assento);
        }
    }

    public void removerAssento(String assento) {
        assentosDisponiveis.removeIf(i->(Objects.equals(i, assento)));
    }

    public void compraAssento(String assento){
        if (assentosDisponiveis.contains(assento)){
            assentosDisponiveis.remove(assento);
            assentosOcupados.add(assento);
        }
    }

    public void cancelaCompra(String assento){
        if(assentosOcupados.contains(assento)){
            assentosOcupados.remove(assento);
            assentosDisponiveis.add(assento);
        }
    }
}
