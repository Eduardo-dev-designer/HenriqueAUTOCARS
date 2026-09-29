package model;

/**
 *
 *
 * @author eduar
 */
public class Score {

    public enum Classificacao {
        ALTO, BOM, BAIXO
    }

    private int id;
    private int clienteId;
    private int pontuacao;
    private Classificacao classificacao;

    public Score() {
    }

    public Score(int clienteId, int pontuacao) {
        this.clienteId = clienteId;
        this.pontuacao = pontuacao;
        this.classificacao = calcularClassificacao(pontuacao);
    }

    public Score(int id, int clienteId, int pontuacao, Classificacao classificacao) {
        this.id = id;
        this.clienteId = clienteId;
        this.pontuacao = pontuacao;
        this.classificacao = classificacao;
    }

    private Classificacao calcularClassificacao(int pontuacao) {
        if (pontuacao >= 700) {
            return Classificacao.ALTO;
        } else if (pontuacao >= 400) {
            return Classificacao.BOM;
        } else {
            return Classificacao.BAIXO;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
        this.classificacao = calcularClassificacao(pontuacao);
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    @Override
    public String toString() {
        return "Score{" + "id=" + id + ", clienteId=" + clienteId
                + ", pontuacao=" + pontuacao + ", classificacao=" + classificacao + '}';
    }
}