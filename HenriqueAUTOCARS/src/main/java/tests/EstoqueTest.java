package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstoqueTest {

    @Test
    void deveAdicionarQuantidadeAoEstoque() {
        Estoque estoque = new Estoque(null, 10);

        estoque.adicionar(5);

        assertEquals(15, estoque.getQuantidade());
    }

    @Test
    void deveRemoverQuantidadeQuandoHaEstoqueSuficiente() {
        Estoque estoque = new Estoque(null, 10);

        estoque.remover(4);

        assertEquals(6, estoque.getQuantidade());
    }

    @Test
    void naoDeveRemoverQuantidadeMaiorQueOEstoque() {
        Estoque estoque = new Estoque(null, 3);

        IllegalArgumentException erro = assertThrows(
                IllegalArgumentException.class,
                () -> estoque.remover(4)
        );

        assertEquals(
                "Quantidade insuficiente em estoque.",
                erro.getMessage()
        );

        assertEquals(3, estoque.getQuantidade());
    }

    @Test
    void deveAlterarDadosDoEstoque() {
        Veiculo veiculo = new Veiculo();
        Estoque estoque = new Estoque();

        estoque.setId(2);
        estoque.setVeiculo(veiculo);
        estoque.setQuantidade(20);

        assertAll(
                () -> assertEquals(2, estoque.getId()),
                () -> assertSame(veiculo, estoque.getVeiculo()),
                () -> assertEquals(20, estoque.getQuantidade())
        );
    }
}