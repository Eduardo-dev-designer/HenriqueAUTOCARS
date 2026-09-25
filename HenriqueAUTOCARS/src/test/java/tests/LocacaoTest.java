package tests;

import model.Locacao;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocacaoTest {

    @Test
    void deveCalcularDiasDaLocacao() {

        LocalDate inicio = LocalDate.of(2026, 9, 1);
        LocalDate fim = LocalDate.of(2026, 9, 6);

        Locacao locacao = new Locacao();

        locacao.setDataInicio(inicio);
        locacao.setDataFim(fim);

        assertEquals(5, locacao.getDias());
    }

    @Test
    void deveCalcularValorTotal() {

        LocalDate inicio = LocalDate.of(2026, 9, 1);
        LocalDate fim = LocalDate.of(2026, 9, 6);

        Locacao locacao = new Locacao();

        locacao.setDataInicio(inicio);
        locacao.setDataFim(fim);
        locacao.setValorDiaria(100);

        assertEquals(500, locacao.getValorTotal());
    }

    @Test
    void deveAlterarValorDaDiaria() {

        Locacao locacao = new Locacao();

        locacao.setValorDiaria(150);

        assertEquals(150, locacao.getValorDiaria());
    }
}

