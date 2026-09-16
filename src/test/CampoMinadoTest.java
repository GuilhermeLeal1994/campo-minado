import model.Tabuleiro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários cobrindo contagem de minas vizinhas, efeito cascata,
 * condição de vitória e sistema de vidas.
 *
 * Os testes que precisam de posições específicas utilizam o construtor
 * de {@link Tabuleiro} que recebe as posições das minas explicitamente,
 * tornando os resultados determinísticos.
 */
public class CampoMinadoTest {

    @Test
    void testContagemDeMinasVizinhas() {
        int[][] minas = {
                {1, 1}
        };

        Tabuleiro tabuleiro =
                new Tabuleiro(3, 3, minas);

        assertEquals(
                1,
                tabuleiro
                        .getCelula(0, 0)
                        .getMinasVizinhas()
        );

        assertEquals(
                1,
                tabuleiro
                        .getCelula(0, 1)
                        .getMinasVizinhas()
        );

        assertEquals(
                1,
                tabuleiro
                        .getCelula(2, 2)
                        .getMinasVizinhas()
        );

        assertEquals(
                0,
                tabuleiro
                        .getCelula(1, 1)
                        .getMinasVizinhas()
        );
    }

    @Test
    void testContagemDeMinasVizinhasComDuasMinasAdjacentes() {
        int[][] minas = {
                {0, 0},
                {0, 1}
        };

        Tabuleiro tabuleiro =
                new Tabuleiro(3, 3, minas);

        assertEquals(
                2,
                tabuleiro
                        .getCelula(1, 0)
                        .getMinasVizinhas()
        );

        assertEquals(
                2,
                tabuleiro
                        .getCelula(0, 2)
                        .getMinasVizinhas()
        );

        assertEquals(
                1,
                tabuleiro
                        .getCelula(2, 2)
                        .getMinasVizinhas()
        );
    }

    @Test
    void testCascataRevelaTodasAsCelulasSemMinasProximas() {
        int[][] semMinas = {};

        Tabuleiro tabuleiro =
                new Tabuleiro(
                        4,
                        4,
                        semMinas
                );

        tabuleiro.revelar(0, 0);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertTrue(
                        tabuleiro
                                .getCelula(i, j)
                                .isRevelada(),
                        "Célula ("
                                + i
                                + ","
                                + j
                                + ") deveria ter sido revelada pela cascata"
                );
            }
        }
    }

    @Test
    void testCascataNaoRevelaMinaNemPassaDelaAdiante() {
        int[][] minas = {
                {2, 2}
        };

        Tabuleiro tabuleiro =
                new Tabuleiro(
                        5,
                        5,
                        minas
                );

        tabuleiro.revelar(0, 0);

        assertFalse(
                tabuleiro
                        .getCelula(2, 2)
                        .isRevelada(),
                "A célula minada nunca deve ser revelada pela cascata"
        );

        assertTrue(
                tabuleiro
                        .getCelula(1, 2)
                        .isRevelada()
        );
    }

    @Test
    void testCliqueEmMinaReduzVidaEMantemOJogoAtivo() {
        int[][] minas = {
                {0, 0},
                {0, 1},
                {0, 2},
                {0, 3},
                {0, 4},
                {0, 5},
                {0, 6},
                {0, 7},
                {0, 8},
                {1, 0}
        };

        Tabuleiro tabuleiro =
                new Tabuleiro(
                        9,
                        9,
                        minas
                );

        assertEquals(
                2,
                tabuleiro.getVidasIniciais()
        );

        assertEquals(
                2,
                tabuleiro.getVidasRestantes()
        );

        tabuleiro.revelar(0, 0);

        assertEquals(
                1,
                tabuleiro.getVidasRestantes()
        );

        assertFalse(
                tabuleiro.isJogoEncerrado()
        );

        assertFalse(
                tabuleiro.isDerrota()
        );

        assertTrue(
                tabuleiro
                        .getCelula(0, 0)
                        .isRevelada()
        );
    }

    @Test
    void testJogoEncerraSomenteQuandoTodasAsVidasForemPerdidas() {
        int[][] minas = {
                {0, 0},
                {0, 1},
                {0, 2},
                {0, 3},
                {0, 4},
                {0, 5},
                {0, 6},
                {0, 7},
                {0, 8},
                {1, 0}
        };

        Tabuleiro tabuleiro =
                new Tabuleiro(
                        9,
                        9,
                        minas
                );

        tabuleiro.revelar(0, 0);

        assertEquals(
                1,
                tabuleiro.getVidasRestantes()
        );

        assertFalse(
                tabuleiro.isJogoEncerrado()
        );

        tabuleiro.revelar(0, 1);

        assertEquals(
                0,
                tabuleiro.getVidasRestantes()
        );

        assertTrue(
                tabuleiro.isJogoEncerrado()
        );

        assertTrue(
                tabuleiro.isDerrota()
        );

        assertTrue(
                tabuleiro
                        .getCelula(0, 1)
                        .isRevelada()
        );
    }

    @Test
    void testQuantidadeDeVidasPorDificuldade() {
        Tabuleiro iniciante =
                new Tabuleiro(
                        9,
                        9,
                        10
                );

        Tabuleiro intermediario =
                new Tabuleiro(
                        16,
                        16,
                        40
                );

        Tabuleiro avancado =
                new Tabuleiro(
                        16,
                        30,
                        99
                );

        assertEquals(
                2,
                iniciante.getVidasIniciais()
        );

        assertEquals(
                3,
                intermediario.getVidasIniciais()
        );

        assertEquals(
                5,
                avancado.getVidasIniciais()
        );

        assertEquals(
                2,
                iniciante.getVidasRestantes()
        );

        assertEquals(
                3,
                intermediario.getVidasRestantes()
        );

        assertEquals(
                5,
                avancado.getVidasRestantes()
        );
    }

    @Test
    void testMarcarEDesmarcarCelulaComBandeira() {
        int[][] minas = {
                {0, 0}
        };

        Tabuleiro tabuleiro =
                new Tabuleiro(
                        3,
                        3,
                        minas
                );

        assertFalse(
                tabuleiro
                        .getCelula(1, 1)
                        .isMarcada()
        );

        tabuleiro.alternarMarcacao(
                1,
                1
        );

        assertTrue(
                tabuleiro
                        .getCelula(1, 1)
                        .isMarcada()
        );

        tabuleiro.alternarMarcacao(
                1,
                1
        );

        assertFalse(
                tabuleiro
                        .getCelula(1, 1)
                        .isMarcada()
        );
    }

    @Test
    void testCelulaMarcadaNaoPodeSerRevelada() {
        int[][] semMinas = {};

        Tabuleiro tabuleiro =
                new Tabuleiro(
                        2,
                        2,
                        semMinas
                );

        tabuleiro.alternarMarcacao(
                0,
                0
        );

        tabuleiro.revelar(
                0,
                0
        );

        assertFalse(
                tabuleiro
                        .getCelula(0, 0)
                        .isRevelada(),
                "Uma célula marcada com bandeira não deve ser revelada"
        );
    }

    @Test
    void testVerificarVitoriaQuandoTodasAsCelulasSeguraForamReveladas() {
        int[][] minas = {
                {0, 0}
        };

        Tabuleiro tabuleiro =
                new Tabuleiro(
                        2,
                        2,
                        minas
                );

        assertFalse(
                tabuleiro.verificarVitoria()
        );

        tabuleiro.revelar(0, 1);
        tabuleiro.revelar(1, 0);
        tabuleiro.revelar(1, 1);

        assertTrue(
                tabuleiro.verificarVitoria()
        );

        assertTrue(
                tabuleiro.isJogoEncerrado()
        );

        assertFalse(
                tabuleiro.isDerrota()
        );
    }

    @Test
    void testVerificarVitoriaEhFalsaEnquantoHouverCelulaSeguraNaoRevelada() {
        int[][] minas = {
                {0, 0}
        };

        Tabuleiro tabuleiro =
                new Tabuleiro(
                        2,
                        2,
                        minas
                );

        tabuleiro.revelar(0, 1);

        assertFalse(
                tabuleiro.verificarVitoria()
        );
    }
}