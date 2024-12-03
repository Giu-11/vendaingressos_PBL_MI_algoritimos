/*******************************************************************************************
 Autor: Giulia Aguiar Loula
 Componente Curricular: EXA863 - MI - PROGRAMAÇÃO
 Concluído em: 18/10/2024
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
    private final String nome;
    private final String descricao;
    private final LocalDate data;
    private final Double precoIngresso;
    private final int totalAssentos;
    private int assentosComprados;
    private final String id;
    private HashMap<String, String> comentarios;
    private final String dataformatada;

    //Construtor
    public Evento(String nome, String descricao, LocalDate data, int totalAssentos) {
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dataString = dataFormatada.format(data);

        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.totalAssentos = totalAssentos;
        this.assentosComprados = 0;
        this.id = dataString + "."+ nome + "." + UUID.randomUUID();
        this.precoIngresso = 0.0;
        this.comentarios = new HashMap<String, String>();

        dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataString = dataFormatada.format(data);

        this.dataformatada = dataString;
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
        this.comentarios = new HashMap<>();

        dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataString = dataFormatada.format(data);

        this.dataformatada = dataString;
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

    public String getDataformatada() {
        return dataformatada;
    }

    /**
     *
     * @return se o evento ainda irá acontecer
     */
    public boolean isAtivo() {
        return data.isAfter(LocalDate.now());
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
     * Adiciona 1 ao contador de ingressos comprados caso ainda não tenha chegado ao limite
     */
    public void compraIngresso(){
        if (this.totalAssentos > this.assentosComprados){
            assentosComprados += 1;
        }
    }

    /**
     * Tira 1 ao contador de ingressos comprados caso ainda não tenha chegado ao limite
     */
    public void cancelaCompra(){
        assentosComprados -= 1;
    }


    /**
     *
     * @param usuario usuário que está fazendo o comentário
     * @param comentario comentário feito no evento
     */
    public void adicionaComentario(Usuario usuario, String comentario){
        if(this.data.isBefore(LocalDate.now())){
            boolean temIngresso = usuario.getIngressos().stream()
                    .anyMatch(ingresso -> Objects.equals(ingresso.getEvento(), this.id));
            if (temIngresso) {
                if(!this.usuarioJaComentou(usuario)) {
                    this.comentarios.put(usuario.getNome(), comentario);
                }
            }
        }
    }

    public Boolean usuarioJaComentou(Usuario usuario){
        return this.comentarios.containsKey(usuario.getNome());
    }
}
