package tests;

import model.Funcionario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {

    @Test
    void deveCriarFuncionario() {

        Funcionario funcionario = new Funcionario();

        funcionario.setId(1);
        funcionario.setNome("Carlos");
        funcionario.setMatricula("MAT001");
        funcionario.setCargo("Vendedor");
        funcionario.setSenha("1234");

        assertEquals(1, funcionario.getId());
        assertEquals("Carlos", funcionario.getNome());
        assertEquals("MAT001", funcionario.getMatricula());
        assertEquals("Vendedor", funcionario.getCargo());
        assertEquals("1234", funcionario.getSenha());
    }

    @Test
    void deveAlterarNomeDoFuncionario() {

        Funcionario funcionario = new Funcionario();

        funcionario.setNome("Pedro");

        assertEquals("Pedro", funcionario.getNome());
    }
}
