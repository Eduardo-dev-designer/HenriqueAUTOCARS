package model;

import java.time.LocalDate;

/**
 *
 * @author eduar
 */
public class Compra {

    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Funcionario funcionario;
    private LocalDate dataCompra;
    private double valorTotal;

    public Compra() {
    }

    public Compra(Cliente cliente, Veiculo veiculo, Funcionario funcionario,
            LocalDate dataCompra, double valorTotal) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.funcionario = funcionario;
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
    }

    public Compra(int id, Cliente cliente, Veiculo veiculo, Funcionario funcionario,
            LocalDate dataCompra, double valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.funcionario = funcionario;
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Compra{" + "id=" + id + ", cliente=" + cliente + ", veiculo=" + veiculo
                + ", funcionario=" + funcionario + ", dataCompra=" + dataCompra
                + ", valorTotal=" + valorTotal + '}';
    }
}