package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTest {

    @Test
    void deveCriarFuncionarioComConstrutor() {
        Funcionario funcionario = new Funcionario(
                "Carlos",
                "MAT001",
                "Vendedor",
                "1234"
        );

        assertAll(
                () -> assertEquals("Carlos", funcionario.getNome()),
                () -> assertEquals("MAT001", funcionario.getMatricula()),
                () -> assertEquals("Vendedor", funcionario.getCargo()),
                () -> assertEquals("1234", funcionario.getSenha())
        );
    }

    @Test
    void deveAlterarDadosDoFuncionario() {
        Funcionario funcionario = new Funcionario();

        funcionario.setId(5);
        funcionario.setNome("Pedro");
        funcionario.setMatricula("MAT005");
        funcionario.setCargo("Gerente");
        funcionario.setSenha("abcd");

        assertAll(
                () -> assertEquals(5, funcionario.getId()),
                () -> assertEquals("Pedro", funcionario.getNome()),
                () -> assertEquals("MAT005", funcionario.getMatricula()),
                () -> assertEquals("Gerente", funcionario.getCargo()),
                () -> assertEquals("abcd", funcionario.getSenha())
        );
    }

    @Test
    void toStringDeveConterDadosPrincipais() {
        Funcionario funcionario = new Funcionario(
                "Carlos",
                "MAT001",
                "Vendedor",
                "1234"
        );

        String texto = funcionario.toString();

        assertTrue(texto.contains("Carlos"));
        assertTrue(texto.contains("MAT001"));
        assertTrue(texto.contains("Vendedor"));
    }
}