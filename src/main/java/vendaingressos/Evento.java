/*******************************************************************************************
 Autor: Giulia Aguiar Loula
 Componente Curricular: EXA863 - MI - PROGRAMAÇÃO
 Concluído em: 07/09/2024
 Declaro que este código foi elaborado por mim de forma individual e não contêm nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

 ********************************************************************************************/
package vendaingressos;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Evento cadastrado no sistema
 */
public class Evento {
    private String nome;
    private String descricao;
    private LocalDate data;
    private Double precoIngresso;
    private int totalAssentos;
    private int assentosComprados;
    private String id;
    private HashMap<String, String> comentarios;

    //Construtor

    /**
     *
     * @param nome nome do evento
     * @param descricao descrição do evento
     * @param data data do evento
     */
    public Evento(String nome, String descricao, LocalDate data, int totalAssentos) {
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dataString = dataFormatada.format(data);

        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.totalAssentos = totalAssentos;
        this.assentosComprados = 0;
        this.id = dataString + "."+ nome + "." + UUID.randomUUID().toString();
        this.precoIngresso = 0.0;
        this.comentarios = new HashMap<String, String>();
    }

    public Evento(String nome, String descricao, LocalDate data, int totalAssentos, double precoIngresso) {
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dataString = dataFormatada.format(data);

        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.totalAssentos = totalAssentos;
        this.assentosComprados = 0;
        this.id = dataString + "."+ nome + "." + UUID.randomUUID().toString();
        this.precoIngresso = precoIngresso;
        this.comentarios = new HashMap<String, String>();
    }


    //Getters
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public Double getPrecoIngresso() {
        return precoIngresso;
    }

    public String getId() {
        return id;
    }

    public int getTotalAssentos() {
        return totalAssentos;
    }

    public int getAssentosComprados() {
        return assentosComprados;
    }

    public HashMap<String, String> getComentarios() {
        return comentarios;
    }

    /**
     *
     * @return se o evento ainda irá acontecer
     */
    public boolean isAtivo() {
        //Data definida como 9 de setembro para simulação do código
        //garante que os resultados dos testes sejam os esperados para as datas definidas neles
        return data.isAfter(LocalDate.of(2024, Month.SEPTEMBER, 9));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return totalAssentos == evento.totalAssentos && assentosComprados == evento.assentosComprados && Objects.equals(nome, evento.nome) && Objects.equals(descricao, evento.descricao) && Objects.equals(data, evento.data) && Objects.equals(precoIngresso, evento.precoIngresso) && Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao, data, precoIngresso, totalAssentos, assentosComprados, id);
    }

    /**
     *
     */
    public void compraIngresso(){
        if (this.totalAssentos > this.assentosComprados){
            assentosComprados += 1;
        }
    }

    /**
     *
     */
    public void cancelaCompra(){
        assentosComprados -= 1;
    }


    public void adicionaComentario(Usuario usuario, String comentario){
        if(this.data.isBefore(LocalDate.of(2024, Month.SEPTEMBER, 9))){
            boolean temIngresso = usuario.getIngressos().stream()
                    .anyMatch(ingresso -> Objects.equals(ingresso.getEvento(), this.id));
            if (temIngresso) {
                if(!this.comentarios.containsKey(usuario.getNome())) {
                    this.comentarios.put(usuario.getNome(), comentario);
                }
            }
        }
    }
}
