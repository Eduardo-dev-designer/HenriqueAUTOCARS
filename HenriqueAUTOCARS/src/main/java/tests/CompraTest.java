/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

/**
 *
 * @author eduar
 */
public class CompraTest {
    
package model;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompraTest {

    @Test
    void deveCriarCompraComConstrutor() {
        Cliente cliente = new Cliente();
        Veiculo veiculo = new Veiculo();
        Funcionario funcionario = new Funcionario();

        LocalDate data = LocalDate.of(2026, 9, 25);

        Compra compra = new Compra(
                cliente,
                veiculo,
                funcionario,
                data,
                85000.00
        );

        assertAll(
                () -> assertSame(cliente, compra.getCliente()),
                () -> assertSame(veiculo, compra.getVeiculo()),
                () -> assertSame(funcionario, compra.getFuncionario()),
                () -> assertEquals(data, compra.getDataCompra()),
                () -> assertEquals(85000.00, compra.getValorTotal())
        );
    }

    @Test
    void deveAlterarDadosDaCompra() {
        Compra compra = new Compra();

        Cliente cliente = new Cliente();
        Veiculo veiculo = new Veiculo();
        Funcionario funcionario = new Funcionario();

        LocalDate data = LocalDate.of(2026, 9, 20);

        compra.setId(4);
        compra.setCliente(cliente);
        compra.setVeiculo(veiculo);
        compra.setFuncionario(funcionario);
        compra.setDataCompra(data);
        compra.setValorTotal(90000);

        assertAll(
                () -> assertEquals(4, compra.getId()),
                () -> assertSame(cliente, compra.getCliente()),
                () -> assertSame(veiculo, compra.getVeiculo()),
                () -> assertSame(funcionario, compra.getFuncionario()),
                () -> assertEquals(data, compra.getDataCompra()),
                () -> assertEquals(90000, compra.getValorTotal())
        );
    }

    @Test
    void toStringDeveConterInformacoesDaCompra() {
        Compra compra = new Compra();

        compra.setId(7);
        compra.setValorTotal(50000);

        String texto = compra.toString();

        assertTrue(texto.contains("id=7"));
        assertTrue(texto.contains("valorTotal=50000.0"));
    }
}
