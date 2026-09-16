package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Representa o tabuleiro do Campo Minado: uma matriz bidimensional de
 * {@link Celula}. É a única classe que conhece a grade inteira, que sabe
 * posicionar minas e calcular vizinhança.
 * <p>
 * Parte do MODEL na arquitetura MVC. Implementa {@link LeituraTabuleiro}
 * para que a View possa consultar o estado do jogo sem depender da API
 * completa (mutável) desta classe.
 */
public class Tabuleiro implements LeituraTabuleiro, Serializable {

    private static final long serialVersionUID = 1L;

    private final int linhas;
    private final int colunas;
    private final int numMinas;
    private final Celula[][] grade;

    private boolean jogoEncerrado;
    private boolean derrota;

    private final int vidasIniciais;
    private int vidasRestantes;

    /**
     * Cria um tabuleiro novo com minas posicionadas aleatoriamente.
     *
     * @param linhas   número de linhas do tabuleiro
     * @param colunas  número de colunas do tabuleiro
     * @param numMinas quantidade de minas a posicionar
     */
    public Tabuleiro(int linhas, int colunas, int numMinas) {
        if (linhas <= 0 || colunas <= 0) {
            throw new IllegalArgumentException("Linhas e colunas devem ser maiores que zero.");
        }


        long totalCelulas = (long) linhas * colunas;

        if (numMinas < 0 || numMinas >= totalCelulas) {
            throw new IllegalArgumentException("Número de minas inválido para esse tabuleiro.");
        }

        this.linhas = linhas;
        this.colunas = colunas;
        this.numMinas = numMinas;
        this.vidasIniciais = determinarVidasIniciais(linhas, colunas, numMinas);
        this.vidasRestantes = vidasIniciais;
        this.grade = new Celula[linhas][colunas];

        inicializarGrade();
        posicionarMinasAleatoriamente();
        calcularMinasVizinhasDeTodasAsCelulas();
    }

    /**
     * Construtor auxiliar que recebe as posições das minas explicitamente,
     * em vez de sortear. Pensado para ser usado em testes unitários, onde
     * é preciso saber exatamente onde as minas estão para verificar o
     * comportamento da cascata e da contagem de vizinhas.
     *
     * @param linhas        número de linhas do tabuleiro
     * @param colunas       número de colunas do tabuleiro
     * @param posicoesMinas array de pares {linha, coluna} com as minas
     */
    public Tabuleiro(int linhas, int colunas, int[][] posicoesMinas) {
        if (linhas <= 0 || colunas <= 0) {
            throw new IllegalArgumentException("Linhas e colunas devem ser maiores que zero.");
        }

        if (posicoesMinas == null) {
            throw new IllegalArgumentException("As posições das minas não podem ser nulas.");
        }

        long totalCelulas =
                (long) linhas * colunas;

        if (posicoesMinas.length >= totalCelulas) {
            throw new IllegalArgumentException("Número de minas inválido para esse tabuleiro.");
        }

        this.linhas = linhas;
        this.colunas = colunas;
        this.numMinas = posicoesMinas.length;
        this.vidasIniciais = determinarVidasIniciais(linhas, colunas, this.numMinas);
        this.vidasRestantes = vidasIniciais;
        this.grade = new Celula[linhas][colunas];

        inicializarGrade();

        for (int[] posicao : posicoesMinas) {
            if (posicao == null || posicao.length < 2
                    || !dentroDosLimites(posicao[0], posicao[1])) {
                throw new IllegalArgumentException("Posição de mina inválida.");
            }

            grade[posicao[0]][posicao[1]].setMinada(true);
        }

        calcularMinasVizinhasDeTodasAsCelulas();
    }

    /**
     * Determina a quantidade de vidas iniciais de acordo com a dificuldade.
     *
     * Iniciante: 9x9 com 10 minas = 2 vidas
     * Intermediário: 16x16 com 40 minas = 3 vidas
     * Avançado: 16x30 com 99 minas = 5 vidas
     *
     * Para tabuleiros auxiliares usados nos testes, que não representam
     * uma das dificuldades oficiais, é utilizado o valor padrão de 2 vidas.
     */
    private static int determinarVidasIniciais(int linhas, int colunas, int numMinas) {
        if (linhas == 9 && colunas == 9 && numMinas == 10) {
            return 2;
        }

        if (linhas == 16 && colunas == 16 && numMinas == 40) {
            return 3;
        }

        if (linhas == 16 && colunas == 30 && numMinas == 99) {
            return 5;
        }

        return 2;
    }

    private void inicializarGrade() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                grade[i][j] = new Celula();
            }
        }
    }

    private void posicionarMinasAleatoriamente() {
        Random sorteio = new Random();
        int minasColocadas = 0;

        while (minasColocadas < numMinas) {
            int linha = sorteio.nextInt(linhas);
            int coluna = sorteio.nextInt(colunas);

            if (!grade[linha][coluna].isMinada()) {
                grade[linha][coluna].setMinada(true);
                minasColocadas++;
            }
        }
    }

    private void calcularMinasVizinhasDeTodasAsCelulas() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                grade[i][j].setMinasVizinhas(contarMinasVizinhas(i, j));
            }
        }
    }

    private int contarMinasVizinhas(int linha, int coluna) {
        int total = 0;

        for (int deltaLinha = -1; deltaLinha <= 1; deltaLinha++) {
            for (int deltaColuna = -1; deltaColuna <= 1; deltaColuna++) {
                if (deltaLinha == 0 && deltaColuna == 0) {
                    continue;
                }

                int vizinhoLinha = linha + deltaLinha;
                int vizinhoColuna = coluna + deltaColuna;

                if (dentroDosLimites(vizinhoLinha, vizinhoColuna)
                        && grade[vizinhoLinha][vizinhoColuna].isMinada()) {
                    total++;
                }
            }
        }

        return total;
    }

    private boolean dentroDosLimites(int linha, int coluna) {
        return linha >= 0
                && linha < linhas
                && coluna >= 0
                && coluna < colunas;
    }

    /**
     * Revela a célula indicada. Se a célula não tiver minas vizinhas, o
     * efeito cascata revela automaticamente as células ao redor (e assim
     * sucessivamente), sem nunca revelar uma célula minada por engano.
     *
     * Quando a célula selecionada é uma mina, uma vida é perdida.
     * O jogo somente é encerrado quando a quantidade de vidas chega a zero.
     *
     * @param linha  linha da célula a revelar
     * @param coluna coluna da célula a revelar
     * @return a lista das células que foram reveladas nesta jogada
     */
    public List<int[]> revelar(int linha, int coluna) {
        List<int[]> ordemRevelacao = new ArrayList<>();

        if (jogoEncerrado || !dentroDosLimites(linha, coluna)) {
            return ordemRevelacao;
        }

        Celula celulaInicial = grade[linha][coluna];

        if (celulaInicial.isRevelada() || celulaInicial.isMarcada()) {
            return ordemRevelacao;
        }

        /*
         * Acertar uma mina não encerra mais imediatamente a partida.
         * A célula é revelada, uma vida é consumida e somente quando
         * não houver mais vidas o jogo entra em estado de derrota.
         */
        if (celulaInicial.isMinada()) {
            celulaInicial.revelar();
            vidasRestantes--;

            ordemRevelacao.add(new int[]{linha, coluna});

            if (vidasRestantes <= 0) {
                vidasRestantes = 0;
                jogoEncerrado = true;
                derrota = true;
            }

            return ordemRevelacao;
        }

        List<int[]> pendentes = new ArrayList<>();
        pendentes.add(new int[]{linha, coluna});

        while (!pendentes.isEmpty()) {
            int[] posicaoAtual = pendentes.remove(pendentes.size() - 1);
            int linhaAtual = posicaoAtual[0];
            int colunaAtual = posicaoAtual[1];

            Celula atual = grade[linhaAtual][colunaAtual];

            if (atual.isRevelada()
                    || atual.isMarcada()
                    || atual.isMinada()) {
                continue;
            }

            atual.revelar();
            ordemRevelacao.add(new int[]{linhaAtual, colunaAtual});

            if (atual.getMinasVizinhas() == 0) {
                for (int deltaLinha = -1; deltaLinha <= 1; deltaLinha++) {
                    for (int deltaColuna = -1; deltaColuna <= 1; deltaColuna++) {
                        if (deltaLinha == 0 && deltaColuna == 0) {
                            continue;
                        }

                        int vizinhoLinha = linhaAtual + deltaLinha;
                        int vizinhoColuna = colunaAtual + deltaColuna;

                        if (dentroDosLimites(vizinhoLinha, vizinhoColuna)) {
                            Celula vizinha = grade[vizinhoLinha][vizinhoColuna];

                            if (!vizinha.isRevelada()
                                    && !vizinha.isMarcada()
                                    && !vizinha.isMinada()) {
                                pendentes.add(new int[]{
                                        vizinhoLinha,
                                        vizinhoColuna
                                });
                            }
                        }
                    }
                }
            }
        }

        if (verificarVitoria()) {
            jogoEncerrado = true;
        }

        return ordemRevelacao;
    }

    /**
     * Marca ou desmarca uma célula com bandeira, sem revelá-la.
     */
    public void alternarMarcacao(int linha, int coluna) {
        if (jogoEncerrado || !dentroDosLimites(linha, coluna)) {
            return;
        }

        grade[linha][coluna].alternarMarcacao();
    }

    /**
     * O jogo é vencido quando todas as células que não são minas já
     * foram reveladas.
     */
    public boolean verificarVitoria() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                Celula celula = grade[i][j];

                if (!celula.isMinada() && !celula.isRevelada()) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean isDerrota() {
        return derrota;
    }

    @Override
    public boolean isJogoEncerrado() {
        return jogoEncerrado;
    }

    @Override
    public int getLinhas() {
        return linhas;
    }

    @Override
    public int getColunas() {
        return colunas;
    }

    public int getNumMinas() {
        return numMinas;
    }

    /**
     * Retorna a quantidade de vidas que o jogador recebeu ao iniciar
     * esta partida.
     */
    public int getVidasIniciais() {
        return vidasIniciais;
    }

    /**
     * Retorna a quantidade de vidas ainda disponíveis.
     */
    public int getVidasRestantes() {
        return vidasRestantes;
    }

    // ----- implementação de LeituraTabuleiro (usada pela View) -----

    @Override
    public boolean isRevelada(int linha, int coluna) {
        return grade[linha][coluna].isRevelada();
    }

    @Override
    public boolean isMarcada(int linha, int coluna) {
        return grade[linha][coluna].isMarcada();
    }

    @Override
    public boolean isMinada(int linha, int coluna) {
        return grade[linha][coluna].isMinada();
    }

    @Override
    public int getMinasVizinhas(int linha, int coluna) {
        return grade[linha][coluna].getMinasVizinhas();
    }

    /**
     * Retorna a célula em uma posição específica. Mantido para uso interno
     * do próprio Model e para os testes unitários — a View nunca deve
     * chamar este método diretamente; ela usa {@link LeituraTabuleiro}.
     */
    public Celula getCelula(int linha, int coluna) {
        return grade[linha][coluna];
    }

    /**
     * Imprime o tabuleiro no console. Quando revelarTudo é true (por
     * exemplo, ao final de uma derrota), mostra também as minas.
     */
    public void imprimir(boolean revelarTudo) {
        StringBuilder cabecalho = new StringBuilder("   ");

        for (int j = 0; j < colunas; j++) {
            cabecalho.append(String.format("%2d", j));
        }

        System.out.println(cabecalho);

        for (int i = 0; i < linhas; i++) {
            StringBuilder linhaTexto =
                    new StringBuilder(String.format("%2d ", i));

            for (int j = 0; j < colunas; j++) {
                Celula celula = grade[i][j];

                if (revelarTudo && celula.isMinada()) {
                    linhaTexto.append(" *");
                } else {
                    linhaTexto.append(" ").append(celula);
                }
            }

            System.out.println(linhaTexto);
        }
    }
}