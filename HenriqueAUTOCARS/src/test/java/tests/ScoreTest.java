package tests;

import model.Score;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreTest {

    @Test
    void deveClassificarScoreBaixo() {

        Score score = new Score(1, 300);

        assertEquals(
                Score.Classificacao.BAIXO,
                score.getClassificacao()
        );
    }

    @Test
    void deveClassificarScoreBom() {

        Score score = new Score(1, 500);

        assertEquals(
                Score.Classificacao.BOM,
                score.getClassificacao()
        );
    }

    @Test
    void deveClassificarScoreAlto() {

        Score score = new Score(1, 800);

        assertEquals(
                Score.Classificacao.ALTO,
                score.getClassificacao()
        );
    }

    @Test
    void deveAlterarPontuacao() {

        Score score = new Score(1, 300);

        score.setPontuacao(800);

        assertEquals(800, score.getPontuacao());

        assertEquals(
                Score.Classificacao.ALTO,
                score.getClassificacao()
        );
    }
}

