package tests;

import model.Veiculo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VeiculoTest {

    @Test
    void deveCriarVeiculo() {

        Veiculo veiculo = new Veiculo();

        veiculo.setId(1);
        veiculo.setMarca("Toyota");
        veiculo.setModelo("Corolla");
        veiculo.setAno(2024);
        veiculo.setCor("Prata");
        veiculo.setKilometragem(15000);
        veiculo.setPlaca("ABC1D23");
        veiculo.setValor(120000);
        veiculo.setDisponivel(true);

        assertEquals(1, veiculo.getId());
        assertEquals("Toyota", veiculo.getMarca());
        assertEquals("Corolla", veiculo.getModelo());
        assertEquals(2024, veiculo.getAno());
        assertEquals("Prata", veiculo.getCor());
        assertEquals(15000, veiculo.getKilometragem());
        assertEquals("ABC1D23", veiculo.getPlaca());
        assertEquals(120000, veiculo.getValor());
        assertTrue(veiculo.isDisponivel());
    }

    @Test
    void deveAlterarDisponibilidade() {

        Veiculo veiculo = new Veiculo();

        veiculo.setDisponivel(false);

        assertFalse(veiculo.isDisponivel());
    }
}

