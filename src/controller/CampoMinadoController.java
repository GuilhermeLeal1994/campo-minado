package controller;

import model.Tabuleiro;
import model.Recordes;
import view.CampoMinadoView;

import javax.swing.Timer;
import java.util.List;

/**
 * CONTROLLER da arquitetura MVC: é o único ponto que conhece tanto o
 * {@link Tabuleiro} (Model) quanto a {@link CampoMinadoView} (View).
 * Recebe notificações de clique da View através de {@link AcoesJogador},
 * aplica a jogada no Model e manda a View se redesenhar.
 *
 * A View nunca toca no Model diretamente, e o Model nunca conhece a View.
 */
public class CampoMinadoController implements AcoesJogador {

    private final CampoMinadoView view;

    private Tabuleiro tabuleiro;
    private int totalMinas;
    private int totalCelulas;
    private int celulasReveladas;
    private int jogadas;
    private boolean jogoIniciado;
    private long tempoInicio;
    private int limiteSegundos;
    private Timer timerJogo;

    private final Recordes recordes =
            new Recordes();

    private String dificuldadeAtual;

    private int dicasUsadas;

    private static final int MAX_DICAS = 3;

    public CampoMinadoController(CampoMinadoView view) {
        this.view = view;
        this.view.setOuvinte(this);
    }

    public void iniciar() {
        view.mostrarTelaInicial();
        atualizarRecordesNaView();
        view.setVisible(true);
    }

    @Override
    public void aoEscolherDificuldade(
            int linhas,
            int colunas,
            int minas) {

        this.tabuleiro =
                new Tabuleiro(
                        linhas,
                        colunas,
                        minas
                );

        this.totalMinas = minas;

        this.totalCelulas =
                linhas * colunas - minas;

        this.celulasReveladas = 0;
        this.jogadas = 0;
        this.jogoIniciado = false;
        this.dicasUsadas = 0;

        this.dificuldadeAtual =
                identificarDificuldade(
                        linhas,
                        colunas,
                        minas
                );

        pararTimer();

        view.aplicarTemaSelecionado();

        this.limiteSegundos =
                view.getTempoLimiteSegundosSelecionado();

        view.iniciarTelaDeJogo(
                linhas,
                colunas,
                totalMinas,
                totalCelulas,
                limiteSegundos,
                tabuleiro.getVidasRestantes()
        );

        view.atualizarEstatisticas(
                totalMinas,
                0,
                totalCelulas,
                0,
                tabuleiro.getVidasRestantes()
        );

        view.atualizarDicas(
                dicasUsadas,
                MAX_DICAS
        );
    }

    @Override
    public void aoPedirNovoJogo() {

        pararTimer();

        view.mostrarTelaInicial();

        atualizarRecordesNaView();
    }

    @Override
    public void aoPedirDica() {

        if (tabuleiro == null
                || tabuleiro.isJogoEncerrado()
                || dicasUsadas >= MAX_DICAS) {

            return;
        }

        int[] segura =
                encontrarCelulaSegura();

        if (segura == null) {

            view.mostrarMensagemDica(
                    "Não há uma célula segura disponível para indicar."
            );

            return;
        }

        dicasUsadas++;

        view.destacarCelulaDica(
                segura[0],
                segura[1]
        );

        view.atualizarDicas(
                dicasUsadas,
                MAX_DICAS
        );

        view.mostrarMensagemDica(
                "Dica: a célula destacada é segura."
        );
    }
@Override
public void aoMostrarTop5() {

    view.mostrarTop5(
            recordes.obterTop5(
                    Recordes.INICIANTE
            ),
            recordes.obterTop5(
                    Recordes.INTERMEDIARIO
            ),
            recordes.obterTop5(
                    Recordes.AVANCADO
            )
    );
}

    @Override
    public void aoMarcarCelula(
            int linha,
            int coluna) {

        if (tabuleiro == null
                || tabuleiro.isJogoEncerrado()) {

            return;
        }

        tabuleiro.alternarMarcacao(
                linha,
                coluna
        );

        view.atualizarCelula(
                linha,
                coluna,
                tabuleiro
        );

        atualizarEstatisticasNaView();
    }

    @Override
    public void aoRevelarCelula(
            int linha,
            int coluna) {

        if (tabuleiro == null
                || tabuleiro.isJogoEncerrado()) {

            return;
        }

        if (!jogoIniciado) {

            jogoIniciado = true;

            tempoInicio =
                    System.currentTimeMillis();

            iniciarTimer();
        }

        if (limiteSegundos > 0
                && obterSegundosPassados() >= limiteSegundos) {

            encerrarPorTempo();

            return;
        }

        /*
         * Evita contar novamente uma célula
         * que já foi revelada.
         */
        if (tabuleiro.isRevelada(
                linha,
                coluna)) {

            return;
        }

        jogadas++;

        List<int[]> reveladas =
                tabuleiro.revelar(
                        linha,
                        coluna
                );

        celulasReveladas =
                contarCelulasReveladas();

        int atraso =
                reveladas.size() > 80
                        ? 3
                        : (reveladas.size() > 25
                        ? 8
                        : 18);

        animarRevelacao(
                reveladas,
                0,
                atraso
        );
    }

    /**
     * Procura uma célula que:
     * - ainda não foi revelada;
     * - não está marcada;
     * - não contém mina.
     */
    private int[] encontrarCelulaSegura() {

        for (int i = 0;
             i < tabuleiro.getLinhas();
             i++) {

            for (int j = 0;
                 j < tabuleiro.getColunas();
                 j++) {

                if (!tabuleiro.isRevelada(i, j)
                        && !tabuleiro.isMarcada(i, j)
                        && !tabuleiro.isMinada(i, j)) {

                    return new int[]{
                            i,
                            j
                    };
                }
            }
        }

        return null;
    }

    /**
     * Identifica a dificuldade a partir
     * das dimensões e quantidade de minas.
     */
    private String identificarDificuldade(
            int linhas,
            int colunas,
            int minas) {

        if (linhas == 9
                && colunas == 9
                && minas == 10) {

            return Recordes.INICIANTE;
        }

        if (linhas == 16
                && colunas == 16
                && minas == 40) {

            return Recordes.INTERMEDIARIO;
        }

        if (linhas == 16
                && colunas == 30
                && minas == 99) {

            return Recordes.AVANCADO;
        }

        return Recordes.INICIANTE;
    }

    /**
     * Atualiza os melhores tempos apresentados
     * na tela inicial.
     *
     * Por enquanto a View recebe o melhor tempo
     * de cada dificuldade.
     */
private void atualizarRecordesNaView() {

    view.atualizarTop5(
            recordes.obterTop5(
                Recordes.INICIANTE),

            recordes.obterTop5(
                Recordes.INTERMEDIARIO),
                
            recordes.obterTop5(
                Recordes.AVANCADO)
    );
}
    private int contarCelulasReveladas() {

        int count = 0;

        for (int i = 0;
             i < tabuleiro.getLinhas();
             i++) {

            for (int j = 0;
                 j < tabuleiro.getColunas();
                 j++) {

                if (tabuleiro.isRevelada(i, j)
                        && !tabuleiro.isMinada(i, j)) {

                    count++;
                }
            }
        }

        return count;
    }

    private int contarMarcadas() {

        int count = 0;

        for (int i = 0;
             i < tabuleiro.getLinhas();
             i++) {

            for (int j = 0;
                 j < tabuleiro.getColunas();
                 j++) {

                if (tabuleiro.isMarcada(i, j)) {
                    count++;
                }
            }
        }

        return count;
    }

    private void atualizarEstatisticasNaView() {

        int restantes =
                totalMinas - contarMarcadas();

        view.atualizarEstatisticas(
                restantes,
                celulasReveladas,
                totalCelulas,
                jogadas,
                tabuleiro.getVidasRestantes()
        );
    }

    private void iniciarTimer() {

        timerJogo =
                new Timer(
                        1000,
                        e -> atualizarTempo()
                );

        timerJogo.start();
    }

    private void pararTimer() {

        if (timerJogo != null) {
            timerJogo.stop();
        }
    }

    private long obterSegundosPassados() {

        return (
                System.currentTimeMillis()
                        - tempoInicio
        ) / 1000;
    }

    private void atualizarTempo() {

        long segundosPassados =
                obterSegundosPassados();

        if (limiteSegundos > 0) {

            long restantes =
                    Math.max(
                            0,
                            limiteSegundos
                                    - segundosPassados
                    );

            view.atualizarTempo(
                    String.format(
                            "-%02d:%02d",
                            restantes / 60,
                            restantes % 60
                    )
            );

            if (restantes <= 0) {

                encerrarPorTempo();

                return;
            }

        } else {

            view.atualizarTempo(
                    String.format(
                            "%02d:%02d",
                            segundosPassados / 60,
                            segundosPassados % 60
                    )
            );
        }
    }

    private void encerrarPorTempo() {

        pararTimer();

        if (tabuleiro != null
                && !tabuleiro.isJogoEncerrado()) {

            tabuleiro =
                    new Tabuleiro(
                            tabuleiro.getLinhas(),
                            tabuleiro.getColunas(),
                            tabuleiro.getNumMinas()
                    );

            /*
             * Não reiniciamos o tabuleiro visualmente.
             * Apenas encerramos a partida por tempo.
             */
        }

        view.mostrarDerrota();

        labelStatusTempoEsgotado();
    }

    private void labelStatusTempoEsgotado() {

        view.mostrarDerrota();
    }

    private void animarRevelacao(
            List<int[]> celulas,
            int indice,
            int atraso) {

        if (indice >= celulas.size()) {

            finalizarJogada();

            return;
        }

        int[] posicao =
                celulas.get(indice);

        view.atualizarCelula(
                posicao[0],
                posicao[1],
                tabuleiro
        );

        Timer timer =
                new Timer(
                        atraso,
                        e -> animarRevelacao(
                                celulas,
                                indice + 1,
                                atraso
                        )
                );

        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Finaliza uma jogada e verifica se a partida terminou.
     *
     * Em caso de vitória:
     * 1. calcula o tempo;
     * 2. verifica se entra no Top 5;
     * 3. somente se entrar, solicita o nome;
     * 4. salva nome + tempo;
     * 5. atualiza os recordes na View.
     */
    private void finalizarJogada() {

        atualizarEstatisticasNaView();

        if (!tabuleiro.isJogoEncerrado()) {
            return;
        }

        pararTimer();

        /*
         * DERROTA
         *
         * Não registra recorde.
         */
        if (tabuleiro.isDerrota()) {

            view.mostrarDerrota();

            animarExplosao();

            return;
        }

        /*
         * VITÓRIA
         */
        int tempoVitoria =
                (int) obterSegundosPassados();

        view.mostrarVitoria();

        /*
         * Só pergunta o nome se o tempo
         * realmente puder entrar no Top 5.
         */
        if (recordes.podeEntrarNoTop5(
                dificuldadeAtual,
                tempoVitoria)) {

            String nome =
                    view.solicitarNomeJogador();

            /*
             * Caso o usuário deixe o nome vazio
             * ou cancele, usamos "Jogador".
             */
            if (nome == null
                    || nome.trim().isEmpty()) {

                nome = "Jogador";
            }

            nome = nome.trim();

            recordes.adicionarRecorde(
                    dificuldadeAtual,
                    nome,
                    tempoVitoria
            );

            view.mostrarMensagemDica(
                    "Você entrou no Top 5 com "
                            + tempoVitoria
                            + " segundo(s)!"
            );

            atualizarRecordesNaView();

        } else {

            /*
             * Vitória que não entrou no Top 5.
             */
            view.mostrarMensagemDica(
                    "Vitória em "
                            + tempoVitoria
                            + " segundo(s)."
            );
        }

        animarVitoria();
    }

    private void animarExplosao() {

        Timer piscar =
                new Timer(
                        100,
                        null
                );

        int[] contador = {0};

        piscar.addActionListener(e -> {

            contador[0]++;

            view.piscarFundoDeExplosao(
                    contador[0] % 2 == 1
            );

            if (contador[0] >= 6) {

                piscar.stop();

                view.piscarFundoDeExplosao(false);

                revelarMinasComAnimacao();
            }
        });

        piscar.start();
    }

    private void revelarMinasComAnimacao() {

        List<int[]> minasNaoReveladas =
                new java.util.ArrayList<>();

        for (int i = 0;
             i < tabuleiro.getLinhas();
             i++) {

            for (int j = 0;
                 j < tabuleiro.getColunas();
                 j++) {

                if (tabuleiro.isMinada(i, j)
                        && !tabuleiro.isRevelada(i, j)) {

                    minasNaoReveladas.add(
                            new int[]{
                                    i,
                                    j
                            }
                    );
                }
            }
        }

        revelarMinasPasso(
                minasNaoReveladas,
                0
        );
    }

    private void revelarMinasPasso(
            List<int[]> minas,
            int indice) {

        if (indice >= minas.size()) {
            return;
        }

        int[] posicao =
                minas.get(indice);

        view.marcarMinaExplodida(
                posicao[0],
                posicao[1]
        );

        Timer timer =
                new Timer(
                        80,
                        e -> revelarMinasPasso(
                                minas,
                                indice + 1
                        )
                );

        timer.setRepeats(false);
        timer.start();
    }

    private void animarVitoria() {

        List<int[]> celulasSeguras =
                new java.util.ArrayList<>();

        for (int i = 0;
             i < tabuleiro.getLinhas();
             i++) {

            for (int j = 0;
                 j < tabuleiro.getColunas();
                 j++) {

                if (tabuleiro.isRevelada(i, j)
                        && !tabuleiro.isMinada(i, j)) {

                    celulasSeguras.add(
                            new int[]{
                                    i,
                                    j
                            }
                    );
                }
            }
        }

        vitoriaPasso(
                celulasSeguras,
                0
        );
    }

    private void vitoriaPasso(
            List<int[]> celulas,
            int indice) {

        if (indice >= celulas.size()) {
            return;
        }

        int[] atual =
                celulas.get(indice);

        view.destacarCelulaVencedora(
                atual[0],
                atual[1]
        );

        Timer timer =
                new Timer(
                        8,
                        e -> vitoriaPasso(
                                celulas,
                                indice + 1
                        )
                );

        timer.setRepeats(false);
        timer.start();
    }
}