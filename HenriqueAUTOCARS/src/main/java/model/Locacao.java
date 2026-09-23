package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author eduar
 */
public class Locacao {

    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Funcionario funcionario;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double valorDiaria;

    public Locacao() {
    }

    public Locacao(Cliente cliente, Veiculo veiculo, Funcionario funcionario,
            LocalDate dataInicio, LocalDate dataFim, double valorDiaria) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.funcionario = funcionario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorDiaria = valorDiaria;
    }

    public Locacao(int id, Cliente cliente, Veiculo veiculo, Funcionario funcionario,
            LocalDate dataInicio, LocalDate dataFim, double valorDiaria) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.funcionario = funcionario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorDiaria = valorDiaria;
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public long getDias() {
        if (dataInicio == null || dataFim == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dataInicio, dataFim);
    }

    public double getValorTotal() {
        return getDias() * valorDiaria;
    }

    @Override
    public String toString() {
        return "Locacao{" + "id=" + id + ", cliente=" + cliente + ", veiculo=" + veiculo
                + ", funcionario=" + funcionario + ", dataInicio=" + dataInicio
                + ", dataFim=" + dataFim + ", valorDiaria=" + valorDiaria + '}';
    }
}