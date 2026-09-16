package model;

import java.io.Serializable;

/**
 * Estado completo necessário para retomar uma partida posteriormente.
 * O objeto é serializável e não possui nenhuma dependência da View.
 */
public class PartidaSalva implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Tabuleiro tabuleiro;
    private final int totalMinas;
    private final int totalCelulas;
    private final int celulasReveladas;
    private final int jogadas;
    private final boolean jogoIniciado;
    private final long tempoDecorridoSegundos;
    private final int limiteSegundos;
    private final String dificuldadeAtual;
    private final int dicasUsadas;

    public PartidaSalva(
            Tabuleiro tabuleiro,
            int totalMinas,
            int totalCelulas,
            int celulasReveladas,
            int jogadas,
            boolean jogoIniciado,
            long tempoDecorridoSegundos,
            int limiteSegundos,
            String dificuldadeAtual,
            int dicasUsadas) {
        this.tabuleiro = tabuleiro;
        this.totalMinas = totalMinas;
        this.totalCelulas = totalCelulas;
        this.celulasReveladas = celulasReveladas;
        this.jogadas = jogadas;
        this.jogoIniciado = jogoIniciado;
        this.tempoDecorridoSegundos = Math.max(0, tempoDecorridoSegundos);
        this.limiteSegundos = Math.max(0, limiteSegundos);
        this.dificuldadeAtual = dificuldadeAtual;
        this.dicasUsadas = Math.max(0, dicasUsadas);
    }

    public Tabuleiro getTabuleiro() { return tabuleiro; }
    public int getTotalMinas() { return totalMinas; }
    public int getTotalCelulas() { return totalCelulas; }
    public int getCelulasReveladas() { return celulasReveladas; }
    public int getJogadas() { return jogadas; }
    public boolean isJogoIniciado() { return jogoIniciado; }
    public long getTempoDecorridoSegundos() { return tempoDecorridoSegundos; }
    public int getLimiteSegundos() { return limiteSegundos; }
    public String getDificuldadeAtual() { return dificuldadeAtual; }
    public int getDicasUsadas() { return dicasUsadas; }
}
