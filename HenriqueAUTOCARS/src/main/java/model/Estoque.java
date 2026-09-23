package model;

/**
 *
 * @author eduar
 */
public class Estoque {

    private int id;
    private Veiculo veiculo;
    private int quantidade;

    public Estoque() {
    }

    public Estoque(Veiculo veiculo, int quantidade) {
        this.veiculo = veiculo;
        this.quantidade = quantidade;
    }

    public Estoque(int id, Veiculo veiculo, int quantidade) {
        this.id = id;
        this.veiculo = veiculo;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void adicionar(int quantidade) {
        this.quantidade += quantidade;
    }

    public void remover(int quantidade) {
        if (quantidade > this.quantidade) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque.");
        }
        this.quantidade -= quantidade;
    }

    @Override
    public String toString() {
        return "Estoque{" + "id=" + id + ", veiculo=" + veiculo + ", quantidade=" + quantidade + '}';
    }
}