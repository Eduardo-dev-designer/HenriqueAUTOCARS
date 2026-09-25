package model;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocacaoTest {

    @Test
    void deveCalcularQuantidadeDeDias() {
        LocalDate inicio = LocalDate.of(2026, 9, 1);
        LocalDate fim = LocalDate.of(2026, 9, 6);

        Locacao locacao = new Locacao(
                null,
                null,
                null,
                inicio,
                fim,
                100.0
        );

        assertEquals(5, locacao.getDias());
    }

    @Test
    void deveCalcularValorTotalDaLocacao() {
        LocalDate inicio = LocalDate.of(2026, 9, 10);
        LocalDate fim = LocalDate.of(2026, 9, 13);

        Locacao locacao = new Locacao(
                null,
                null,
                null,
                inicio,
                fim,
                150.0
        );

        assertEquals(450.0, locacao.getValorTotal());
    }

    @Test
    void deveRetornarZeroQuandoDatasForemNulas() {
        Locacao locacao = new Locacao();

        assertEquals(0, locacao.getDias());
        assertEquals(0.0, locacao.getValorTotal());
    }

    @Test
    void deveAlterarDadosDaLocacao() {
        Locacao locacao = new Locacao();

        Cliente cliente = new Cliente();
        Veiculo veiculo = new Veiculo();
        Funcionario funcionario = new Funcionario();

        LocalDate inicio = LocalDate.of(2026, 10, 1);
        LocalDate fim = LocalDate.of(2026, 10, 5);

        locacao.setId(8);
        locacao.setCliente(cliente);
        locacao.setVeiculo(veiculo);
        locacao.setFuncionario(funcionario);
        locacao.setDataInicio(inicio);
        locacao.setDataFim(fim);
        locacao.setValorDiaria(200);

        assertAll(
                () -> assertEquals(8, locacao.getId()),
                () -> assertSame(cliente, locacao.getCliente()),
                () -> assertSame(veiculo, locacao.getVeiculo()),
                () -> assertSame(funcionario, locacao.getFuncionario()),
                () -> assertEquals(inicio, locacao.getDataInicio()),
                () -> assertEquals(fim, locacao.getDataFim()),
                () -> assertEquals(200, locacao.getValorDiaria()),
                () -> assertEquals(800, locacao.getValorTotal())
        );
    }
}