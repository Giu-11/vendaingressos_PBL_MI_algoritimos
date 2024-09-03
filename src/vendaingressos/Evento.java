package vendaingressos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Evento {
    private String nome;
    private String descricao;
    private Date data;
    private List<String> assentosDisponiveis;

    public Evento(String nome, String descricao, Date data) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.assentosDisponiveis = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getData() {
        return data;
    }

    public void adicionarAssento(String assento) {
        assentosDisponiveis.add(assento);
    }

    public List<String> getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public void removerAssento(String assento) {
        int i=0;
        boolean encontado = false;
        while((i < (assentosDisponiveis.size()-1)) || (!encontado)){
            if (!Objects.equals(assentosDisponiveis.get(i), assento)){
                encontado = true;
                assentosDisponiveis.remove(i);
            }else{
                i++;
            }
        }
    }

    public boolean isAtivo() {
        //
    }
}
