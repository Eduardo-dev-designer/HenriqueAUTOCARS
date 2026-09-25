package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VeiculoTest {

    @Test
    void deveCriarVeiculoDisponivelPorPadrao() {
        Veiculo veiculo = new Veiculo(
                "Toyota",
                "Corolla",
                2024,
                "Prata",
                15000.5,
                "ABC1D23",
                120000.00
        );

        assertAll(
                () -> assertEquals("Toyota", veiculo.getMarca()),
                () -> assertEquals("Corolla", veiculo.getModelo()),
                () -> assertEquals(2024, veiculo.getAno()),
                () -> assertEquals("Prata", veiculo.getCor()),
                () -> assertEquals(15000.5, veiculo.getKilometragem()),
                () -> assertEquals("ABC1D23", veiculo.getPlaca()),
                () -> assertEquals(120000.00, veiculo.getValor()),
                () -> assertTrue(veiculo.isDisponivel())
        );
    }

    @Test
    void deveAlterarDisponibilidade() {
        Veiculo veiculo = new Veiculo();

        veiculo.setDisponivel(false);

        assertFalse(veiculo.isDisponivel());

        veiculo.setDisponivel(true);

        assertTrue(veiculo.isDisponivel());
    }

    @Test
    void deveAlterarDadosDoVeiculo() {
        Veiculo veiculo = new Veiculo();

        veiculo.setId(3);
        veiculo.setMarca("Honda");
        veiculo.setModelo("Civic");
        veiculo.setAno(2023);
        veiculo.setCor("Preto");
        veiculo.setKilometragem(25000);
        veiculo.setPlaca("XYZ9A99");
        veiculo.setValor(110000);

        assertAll(
                () -> assertEquals(3, veiculo.getId()),
                () -> assertEquals("Honda", veiculo.getMarca()),
                () -> assertEquals("Civic", veiculo.getModelo()),
                () -> assertEquals(2023, veiculo.getAno()),
                () -> assertEquals("Preto", veiculo.getCor()),
                () -> assertEquals(25000, veiculo.getKilometragem()),
                () -> assertEquals("XYZ9A99", veiculo.getPlaca()),
                () -> assertEquals(110000, veiculo.getValor())
        );
    }
}