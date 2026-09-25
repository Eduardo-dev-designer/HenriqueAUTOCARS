package tests;

import model.Cliente;
import model.Score;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    void deveCriarCliente() {

        Cliente cliente = new Cliente();

        cliente.setId(1);
        cliente.setNome("João");
        cliente.setCpf("12345678900");
        cliente.setEmail("joao@email.com");
        cliente.setTelefone("11999999999");
        cliente.setSenha("1234");

        assertEquals(1, cliente.getId());
        assertEquals("João", cliente.getNome());
        assertEquals("12345678900", cliente.getCpf());
        assertEquals("joao@email.com", cliente.getEmail());
        assertEquals("11999999999", cliente.getTelefone());
        assertEquals("1234", cliente.getSenha());
    }

    @Test
    void deveAssociarScoreAoCliente() {

        Cliente cliente = new Cliente();
        Score score = new Score(1, 800);

        cliente.setScore(score);

            assertSame(score, cliente.getScore());
    }
}

