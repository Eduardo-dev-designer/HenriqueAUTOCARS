package tests;

import model.Estoque;
import model.Veiculo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EstoqueTest {

    @Test
    void deveCriarEstoque() {

        Estoque estoque = new Estoque();

        estoque.setId(1);
        estoque.setQuantidade(10);

        assertEquals(1, estoque.getId());
        assertEquals(10, estoque.getQuantidade());
    }

    @Test
    void deveAdicionarQuantidade() {

        Estoque estoque = new Estoque();

        estoque.setQuantidade(10);
        estoque.adicionar(5);

        assertEquals(15, estoque.getQuantidade());
    }

    @Test
    void deveRemoverQuantidade() {

        Estoque estoque = new Estoque();

        estoque.setQuantidade(10);
        estoque.remover(4);

        assertEquals(6, estoque.getQuantidade());
    }

    @Test
    void deveAssociarVeiculoAoEstoque() {

        Estoque estoque = new Estoque();
        Veiculo veiculo = new Veiculo();

        estoque.setVeiculo(veiculo);

        assertSame(veiculo, estoque.getVeiculo());
    }
}
