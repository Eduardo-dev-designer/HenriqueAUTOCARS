package model;

/**
 *
 * @author eduar
 */
public class Veiculo {

    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
    private double kilometragem;
    private String placa;
    private double valor;
    private boolean disponivel;

    public Veiculo() {
    }

    public Veiculo(String marca, String modelo, int ano, String cor,
            double kilometragem, String placa, double valor) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.kilometragem = kilometragem;
        this.placa = placa;
        this.valor = valor;
        this.disponivel = true;
    }

    public Veiculo(int id, String marca, String modelo, int ano, String cor,
            double kilometragem, String placa, double valor, boolean disponivel) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.kilometragem = kilometragem;
        this.placa = placa;
        this.valor = valor;
        this.disponivel = disponivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(double kilometragem) {
        this.kilometragem = kilometragem;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "id=" + id + ", marca=" + marca + ", modelo=" + modelo
                + ", ano=" + ano + ", cor=" + cor + ", kilometragem=" + kilometragem
                + ", placa=" + placa + ", valor=" + valor + ", disponivel=" + disponivel + '}';
    }
}