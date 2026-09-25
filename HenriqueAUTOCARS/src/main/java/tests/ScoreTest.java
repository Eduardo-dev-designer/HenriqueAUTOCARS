package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void pontuacao700DeveSerClassificadaComoAlto() {
        Score score = new Score(1, 700);

        assertEquals(
                Score.Classificacao.ALTO,
                score.getClassificacao()
        );
    }

    @Test
    void pontuacaoMaiorQue700DeveSerClassificadaComoAlto() {
        Score score = new Score(1, 900);

        assertEquals(
                Score.Classificacao.ALTO,
                score.getClassificacao()
        );
    }

    @Test
    void pontuacaoEntre400E699DeveSerClassificadaComoBom() {
        Score score = new Score(1, 500);

        assertEquals(
                Score.Classificacao.BOM,
                score.getClassificacao()
        );
    }

    @Test
    void pontuacaoMenorQue400DeveSerClassificadaComoBaixo() {
        Score score = new Score(1, 399);

        assertEquals(
                Score.Classificacao.BAIXO,
                score.getClassificacao()
        );
    }

    @Test
    void alterarPontuacaoDeveAtualizarClassificacao() {
        Score score = new Score(1, 300);

        assertEquals(
                Score.Classificacao.BAIXO,
                score.getClassificacao()
        );

        score.setPontuacao(800);

        assertEquals(800, score.getPontuacao());

        assertEquals(
                Score.Classificacao.ALTO,
                score.getClassificacao()
        );
    }

    @Test
    void deveAlterarIdECliente() {
        Score score = new Score();

        score.setId(10);
        score.setClienteId(25);
        score.setClassificacao(Score.Classificacao.BOM);

        assertAll(
                () -> assertEquals(10, score.getId()),
                () -> assertEquals(25, score.getClienteId()),
                () -> assertEquals(
                        Score.Classificacao.BOM,
                        score.getClassificacao()
                )
        );
    }
}