package tests;

import model.Cliente;
import model.Compra;
import model.Funcionario;
import model.Veiculo;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompraTest {

    @Test
    void deveCriarCompra() {

        Compra compra = new Compra();

        Cliente cliente = new Cliente();
        Veiculo veiculo = new Veiculo();
        Funcionario funcionario = new Funcionario();

        LocalDate data = LocalDate.of(2026, 9, 25);

        compra.setId(1);
        compra.setCliente(cliente);
        compra.setVeiculo(veiculo);
        compra.setFuncionario(funcionario);
        compra.setDataCompra(data);
        compra.setValorTotal(85000);

        assertEquals(1, compra.getId());
        assertSame(cliente, compra.getCliente());
        assertSame(veiculo, compra.getVeiculo());
        assertSame(funcionario, compra.getFuncionario());
        assertEquals(data, compra.getDataCompra());
        assertEquals(85000, compra.getValorTotal());
    }

    @Test
    void deveAlterarValorDaCompra() {

        Compra compra = new Compra();

        compra.setValorTotal(90000);

        assertEquals(90000, compra.getValorTotal());
    }
}

