package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModelSmokeTest {

    @Test
    void devePermitirCriarTodosOsModelosComConstrutorVazio() {

        assertAll(
                () -> assertNotNull(new Cliente()),
                () -> assertNotNull(new Compra()),
                () -> assertNotNull(new Estoque()),
                () -> assertNotNull(new Funcionario()),
                () -> assertNotNull(new Locacao()),
                () -> assertNotNull(new Score()),
                () -> assertNotNull(new Veiculo())
        );
    }
}