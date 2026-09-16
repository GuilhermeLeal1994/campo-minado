package view;

import controller.AcoesJogador;
import model.LeituraTabuleiro;
import model.Recordes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * VIEW da arquitetura MVC do Campo Minado.
 *
 * Responsável exclusivamente pela interface gráfica, temas, animações e
 * encaminhamento das ações do jogador para o Controller.
 *
 * A View não acessa o Model diretamente para tomar decisões de jogo.
 */
public class CampoMinadoView extends JFrame {

    // ================================================================
    // Constantes visuais
    // ================================================================

    private static final Color COR_FUNDO =
            new Color(18, 18, 24);

    private static final Color COR_FUNDO_CLARO =
            new Color(32, 32, 42);

    private static final Color COR_DESTAQUE =
            new Color(0, 255, 220);

    private static final Color COR_TEXTO_PRINCIPAL =
            new Color(240, 240, 245);

    private static final Color COR_TEXTO_SECUNDARIO =
            new Color(165, 170, 185);

    private static final Color COR_CARD =
            new Color(28, 28, 38);

    private static final Color COR_CARD_HOVER =
            new Color(40, 40, 52);

    private static final Color COR_BORDA =
            new Color(70, 70, 90);

    private static final Color COR_CELULA_OCULTA =
            new Color(45, 45, 58);

    private static final Color COR_CELULA_OCULTA_HOVER =
            new Color(65, 65, 82);

    private static final Color COR_BORDA_OCULTA =
            new Color(85, 85, 105);

    private static final Color COR_CELULA_REVELADA =
            new Color(28, 28, 36);

    private static final Color COR_BORDA_REVELADA =
            new Color(65, 65, 78);

    private static final Color COR_TEXTO_SOBRE_REVELADA =
            new Color(235, 235, 240);

    private static final Color COR_MINA_FUNDO =
            new Color(120, 25, 35);

    private static final Color COR_MINA =
            new Color(255, 80, 95);

    private static final Color COR_VITORIA =
            new Color(90, 255, 130);

    private static final Color COR_BANDEIRA =
            new Color(255, 80, 170);

    private static final Color[] CORES_NUMEROS = {
            new Color(70, 180, 255),
            new Color(90, 255, 130),
            new Color(255, 90, 100),
            new Color(190, 100, 255),
            new Color(255, 150, 60),
            new Color(70, 230, 220),
            new Color(255, 100, 190),
            new Color(210, 210, 220)
    };

    private static final Color COR_FUNDO_CLARO_TEMA =
            new Color(242, 244, 248);

    private static final Color COR_CARD_CLARO_TEMA =
            new Color(255, 255, 255);

    private static final Color COR_CARD_HOVER_CLARO_TEMA =
            new Color(235, 240, 246);

    private static final Color COR_TEXTO_PRINCIPAL_CLARO_TEMA =
            new Color(35, 38, 45);

    private static final Color COR_TEXTO_SECUNDARIO_CLARO_TEMA =
            new Color(90, 96, 108);

    private static final Color COR_BORDA_CLARO_TEMA =
            new Color(185, 192, 204);

    private static final Color COR_CELULA_OCULTA_CLARO_TEMA =
            new Color(222, 227, 235);

    private static final Color COR_CELULA_OCULTA_HOVER_CLARO_TEMA =
            new Color(205, 212, 223);

    private static final Color COR_BORDA_OCULTA_CLARO_TEMA =
            new Color(170, 178, 190);

    private static final Color COR_CELULA_REVELADA_CLARO_TEMA =
            new Color(250, 250, 252);

    private static final Color COR_BORDA_REVELADA_CLARO_TEMA =
            new Color(205, 210, 218);

    private static final Color COR_TEXTO_SOBRE_REVELADA_CLARO_TEMA =
            new Color(35, 38, 45);

    private static final Color COR_MINA_FUNDO_CLARO_TEMA =
            new Color(255, 215, 220);

    private static final Color COR_MINA_CLARO_TEMA =
            new Color(190, 30, 50);

    private static final Color COR_VITORIA_CLARO_TEMA =
            new Color(25, 145, 70);

    private static final Color COR_BANDEIRA_CLARO_TEMA =
            new Color(205, 30, 125);

    private static final Color[] CORES_NUMEROS_CLARO_TEMA = {
            new Color(20, 105, 180),
            new Color(25, 140, 65),
            new Color(205, 35, 50),
            new Color(120, 55, 185),
            new Color(190, 95, 15),
            new Color(20, 145, 135),
            new Color(190, 45, 135),
            new Color(80, 85, 95)
    };

    private static final Color CYBER_FUNDO =
            new Color(15, 5, 28);

    private static final Color CYBER_FUNDO_CLARO =
            new Color(35, 15, 52);

    private static final Color CYBER_DESTAQUE =
            new Color(0, 255, 255);

    private static final Color CYBER_ROSA =
            new Color(255, 20, 180);

    private static final Color CYBER_TEXTO =
            new Color(245, 230, 255);

    private static final Color CYBER_TEXTO_SECUNDARIO =
            new Color(190, 160, 210);

    private static final Color CYBER_CARD =
            new Color(28, 10, 45);

    private static final Color CYBER_CARD_HOVER =
            new Color(45, 15, 65);

    private static final Color CYBER_BORDA =
            new Color(130, 40, 150);

    private static final Color CYBER_CELULA =
            new Color(40, 12, 60);

    private static final Color CYBER_CELULA_HOVER =
            new Color(65, 18, 90);

    private static final Color CYBER_BORDA_CELULA =
            new Color(150, 45, 175);

    private static final Color CYBER_REVELADA =
            new Color(25, 12, 38);

    private static final Color CYBER_BORDA_REVELADA =
            new Color(100, 35, 125);

    private static final Color CYBER_MINA_FUNDO =
            new Color(100, 10, 55);

    private static final Color CYBER_MINA =
            new Color(255, 50, 150);

    private static final Color CYBER_VITORIA =
            new Color(50, 255, 160);

    private static final Color CYBER_BANDEIRA =
            new Color(255, 40, 190);

    private static final Color TERMINAL_FUNDO =
            new Color(5, 15, 5);

    private static final Color TERMINAL_FUNDO_CLARO =
            new Color(12, 28, 12);

    private static final Color TERMINAL_DESTAQUE =
            new Color(80, 255, 80);

    private static final Color TERMINAL_TEXTO =
            new Color(120, 255, 120);

    private static final Color TERMINAL_TEXTO_SECUNDARIO =
            new Color(70, 180, 70);

    private static final Color TERMINAL_CARD =
            new Color(8, 25, 8);

    private static final Color TERMINAL_CARD_HOVER =
            new Color(12, 40, 12);

    private static final Color TERMINAL_BORDA =
            new Color(35, 110, 35);

    private static final Color TERMINAL_CELULA =
            new Color(12, 35, 12);

    private static final Color TERMINAL_CELULA_HOVER =
            new Color(20, 55, 20);

    private static final Color TERMINAL_BORDA_CELULA =
            new Color(45, 135, 45);

    private static final Color TERMINAL_REVELADA =
            new Color(6, 22, 6);

    private static final Color TERMINAL_BORDA_REVELADA =
            new Color(30, 90, 30);

    private static final Color TERMINAL_MINA_FUNDO =
            new Color(80, 20, 20);

    private static final Color TERMINAL_MINA =
            new Color(255, 70, 70);

    private static final Color TERMINAL_VITORIA =
            new Color(100, 255, 100);

    private static final Color TERMINAL_BANDEIRA =
            new Color(150, 255, 80);

    private static final Font FONTE_CELULA =
            new Font("SansSerif", Font.BOLD, 16);

    private static final Font FONTE_TITULO =
            new Font("SansSerif", Font.BOLD, 30);

    private static final Font FONTE_SUBTITULO =
            new Font("SansSerif", Font.BOLD, 20);

    private static final Font FONTE_NORMAL =
            new Font("SansSerif", Font.PLAIN, 15);

    private static final Font FONTE_NUMERO =
            new Font("SansSerif", Font.BOLD, 24);

    private static final Font FONTE_PEQUENA =
            new Font("SansSerif", Font.PLAIN, 13);

    private static final Font FONTE_TERMINAL_CELULA =
            new Font("Monospaced", Font.BOLD, 16);

    private static final Font FONTE_TERMINAL_TITULO =
            new Font("Monospaced", Font.BOLD, 28);

    private static final Font FONTE_TERMINAL_SUBTITULO =
            new Font("Monospaced", Font.BOLD, 18);

    private static final Font FONTE_TERMINAL_NORMAL =
            new Font("Monospaced", Font.PLAIN, 14);

    private static final Font FONTE_TERMINAL_NUMERO =
            new Font("Monospaced", Font.BOLD, 22);

    private static final Font FONTE_TERMINAL_PEQUENA =
            new Font("Monospaced", Font.PLAIN, 12);

    private static final String EMOJI_BOMBA =
            "\uD83D\uDCA3";

    private static final String EMOJI_RELOGIO =
            "\u23F1\uFE0F";

    private static final String EMOJI_ICONE_ESTATISTICA =
            "\uD83D\uDCCA";

    private static final String EMOJI_JOGADA =
            "\uD83C\uDFAF";

    private static final String EMOJI_BANDEIRA =
            "\uD83D\uDEA9";

    private static final String EMOJI_VIDA =
            "\u2764\uFE0F";

    // ================================================================
    // Componentes
    // ================================================================

    private AcoesJogador ouvinte;

    private JPanel painelPrincipal;
    private JPanel painelTabuleiro;

    private JLabel labelStatus;
    private JLabel lblTempo;
    private JLabel lblMinasRestantes;
    private JLabel lblCelulasReveladas;
    private JLabel lblJogadas;
    private JLabel lblVidas;
    private JLabel lblRecordes;
    private JButton botaoDica;
    private JButton botaoTop5;
    private JProgressBar barraProgresso;

    private JComboBox<String> comboTemaFundo;
    private JComboBox<String> comboTemaVisual;
    private JComboBox<String> comboTemaTabuleiro;
    private JComboBox<String> comboTempo;

    private Color corFundo = COR_FUNDO;
    private Color corFundoClaro = COR_FUNDO_CLARO;
    private Color corDestaque = COR_DESTAQUE;
    private Color corTextoPrincipal = COR_TEXTO_PRINCIPAL;
    private Color corTextoSecundario = COR_TEXTO_SECUNDARIO;
    private Color corCard = COR_CARD;
    private Color corCardHover = COR_CARD_HOVER;
    private Color corBorda = COR_BORDA;
    private Color corCelulaOculta = COR_CELULA_OCULTA;
    private Color corCelulaOcultaHover = COR_CELULA_OCULTA_HOVER;
    private Color corBordaOculta = COR_BORDA_OCULTA;
    private Color corCelulaRevelada = COR_CELULA_REVELADA;
    private Color corBordaRevelada = COR_BORDA_REVELADA;
    private Color corTextoSobreRevelada =
            COR_TEXTO_SOBRE_REVELADA;
    private Color corMinaFundo = COR_MINA_FUNDO;
    private Color corMina = COR_MINA;
    private Color corVitoria = COR_VITORIA;
    private Color corBandeira = COR_BANDEIRA;
    private Color[] coresNumeros = CORES_NUMEROS;

    private Font fonteCelula = FONTE_CELULA;
    private Font fonteTitulo = FONTE_TITULO;
    private Font fonteSubtitulo = FONTE_SUBTITULO;
    private Font fonteNormal = FONTE_NORMAL;
    private Font fonteNumero = FONTE_NUMERO;
    private Font fontePequena = FONTE_PEQUENA;

    // Controle da tela cheia (F11). A View mantém o estado visual da janela
    // sem alterar nenhuma regra do jogo ou depender do Controller.
    private boolean telaCheia = false;
    private Rectangle limitesJanelaNormal;
    private boolean resizableAntesDaTelaCheia;
    private GraphicsDevice dispositivoTelaCheia;

    private static final String CHAVE_FONTE_ORIGINAL =
            "fonteOriginalTelaCheia";

    private static final String CHAVE_PREFERIDO_ORIGINAL =
            "preferidoOriginalTelaCheia";

    private static final String CHAVE_MINIMO_ORIGINAL =
            "minimoOriginalTelaCheia";

    private static final String CHAVE_MAXIMO_ORIGINAL =
            "maximoOriginalTelaCheia";

    public CampoMinadoView() {
    super("Campo Minado");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(COR_FUNDO);
    setResizable(false);
    configurarAtalhoTelaCheia();

    setMinimumSize(new Dimension(900, 700));
    setSize(1000, 750);
    setLocationRelativeTo(null);
}
    /**
     * Configura o atalho global da janela para alternar o modo de tela cheia
     * com F11. O binding fica no {@link JRootPane} para continuar funcionando
     * mesmo quando um botão, combo ou outro componente estiver em foco.
     */
    private void configurarAtalhoTelaCheia() {
        KeyStroke teclaF11 =
                KeyStroke.getKeyStroke("F11");

        getRootPane()
                .getInputMap(
                        JComponent.WHEN_IN_FOCUSED_WINDOW
                )
                .put(
                        teclaF11,
                        "alternarTelaCheia"
                );

        getRootPane()
                .getActionMap()
                .put(
                        "alternarTelaCheia",
                        new AbstractAction() {
                            @Override
                            public void actionPerformed(
                                    java.awt.event.ActionEvent e
                            ) {
                                alternarTelaCheia();
                            }
                        }
                );
    }

    /** Alterna entre o tamanho normal da janela e a tela cheia. */
    private void alternarTelaCheia() {
        if (telaCheia) {
            sairDaTelaCheia();
        } else {
            entrarEmTelaCheia();
        }
    }

    /**
     * Entra em tela cheia usando o dispositivo gráfico principal. A interface
     * é ampliada proporcionalmente para aproveitar a área disponível sem
     * deformar fontes ou componentes.
     */
    private void entrarEmTelaCheia() {
        if (telaCheia) {
            return;
        }

        if (getGraphicsConfiguration() == null) {
            return;
        }

        salvarTamanhosOriginais(
                getContentPane()
        );

        limitesJanelaNormal =
                getBounds();

        resizableAntesDaTelaCheia =
                isResizable();

        dispositivoTelaCheia =
                getGraphicsConfiguration().getDevice();

        Rectangle areaTela =
                dispositivoTelaCheia
                        .getDefaultConfiguration()
                        .getBounds();

        Insets insets =
                Toolkit.getDefaultToolkit()
                        .getScreenInsets(
                                dispositivoTelaCheia
                                        .getDefaultConfiguration()
                        );

        int larguraDisponivel =
                Math.max(
                        1,
                        areaTela.width
                                - insets.left
                                - insets.right
                );

        int alturaDisponivel =
                Math.max(
                        1,
                        areaTela.height
                                - insets.top
                                - insets.bottom
                );

        int larguraAtual =
                Math.max(
                        1,
                        getWidth()
                );

        int alturaAtual =
                Math.max(
                        1,
                        getHeight()
                );

        double escalaHorizontal =
                (double) larguraDisponivel
                        / larguraAtual;

        double escalaVertical =
                (double) alturaDisponivel
                        / alturaAtual;

        double escala =
                Math.max(
                        1.0,
                        Math.min(
                                escalaHorizontal,
                                escalaVertical
                        )
                );

        aplicarEscalaTelaCheia(
                escala
        );

        dispose();

        setUndecorated(true);
        setResizable(false);

        if (dispositivoTelaCheia.isFullScreenSupported()) {
            dispositivoTelaCheia.setFullScreenWindow(this);
        } else {
            setBounds(
                    areaTela.x + insets.left,
                    areaTela.y + insets.top,
                    larguraDisponivel,
                    alturaDisponivel
            );

            setVisible(true);
        }

        telaCheia = true;

        atualizarTextoBotaoTelaCheia();

        revalidate();
        repaint();
    }

    /**
     * Sai do modo de tela cheia e restaura as dimensões originais da interface.
     */
    private void sairDaTelaCheia() {
        if (!telaCheia) {
            return;
        }

        if (dispositivoTelaCheia != null
                && dispositivoTelaCheia.isFullScreenSupported()
                && dispositivoTelaCheia.getFullScreenWindow() == this) {
            dispositivoTelaCheia.setFullScreenWindow(null);
        }

        dispose();

        setUndecorated(false);

        restaurarEscalaTelaCheia();

        setResizable(
                resizableAntesDaTelaCheia
        );

        if (limitesJanelaNormal != null) {
            setBounds(
                    limitesJanelaNormal
            );
        }

        setVisible(true);

        telaCheia = false;

        atualizarTextoBotaoTelaCheia();

        revalidate();
        repaint();
    }

    /**
     * Percorre os componentes da interface e guarda os tamanhos e fontes
     * necessários para restaurar a View após a saída da tela cheia.
     */
    private void salvarTamanhosOriginais(
            Component componente
    ) {
        if (componente == null) {
            return;
        }

        if (componente instanceof JComponent) {
            JComponent jc =
                    (JComponent) componente;

            if (jc.getClientProperty(
                    CHAVE_FONTE_ORIGINAL
            ) == null) {
                jc.putClientProperty(
                        CHAVE_FONTE_ORIGINAL,
                        jc.getFont()
                );
            }

            if (jc.getClientProperty(
                    CHAVE_PREFERIDO_ORIGINAL
            ) == null) {
                jc.putClientProperty(
                        CHAVE_PREFERIDO_ORIGINAL,
                        jc.getPreferredSize()
                );
            }

            if (jc.getClientProperty(
                    CHAVE_MINIMO_ORIGINAL
            ) == null) {
                jc.putClientProperty(
                        CHAVE_MINIMO_ORIGINAL,
                        jc.getMinimumSize()
                );
            }

            if (jc.getClientProperty(
                    CHAVE_MAXIMO_ORIGINAL
            ) == null) {
                jc.putClientProperty(
                        CHAVE_MAXIMO_ORIGINAL,
                        jc.getMaximumSize()
                );
            }
        }

        if (componente instanceof Container) {
            Component[] filhos =
                    ((Container) componente)
                            .getComponents();

            for (Component filho : filhos) {
                salvarTamanhosOriginais(
                        filho
                );
            }
        }
    }

    /**
     * Aplica uma escala uniforme aos componentes visuais da janela.
     */
    private void aplicarEscalaTelaCheia(
            double escala
    ) {
        escalarComponente(
                getContentPane(),
                escala
        );
    }

    private void escalarComponente(
            Component componente,
            double escala
    ) {
        if (componente instanceof JComponent) {
            JComponent jc =
                    (JComponent) componente;

            Object fonteOriginal =
                    jc.getClientProperty(
                            CHAVE_FONTE_ORIGINAL
                    );

            if (fonteOriginal instanceof Font) {
                Font fonte =
                        (Font) fonteOriginal;

                float tamanho =
                        (float) (
                                fonte.getSize2D()
                                        * escala
                        );

                jc.setFont(
                        fonte.deriveFont(
                                Math.max(
                                        1f,
                                        tamanho
                                )
                        )
                );
            }

            Object preferido =
                    jc.getClientProperty(
                            CHAVE_PREFERIDO_ORIGINAL
                    );

            if (preferido instanceof Dimension) {
                jc.setPreferredSize(
                        escalarDimension(
                                (Dimension) preferido,
                                escala
                        )
                );
            }

            Object minimo =
                    jc.getClientProperty(
                            CHAVE_MINIMO_ORIGINAL
                    );

            if (minimo instanceof Dimension) {
                jc.setMinimumSize(
                        escalarDimension(
                                (Dimension) minimo,
                                escala
                        )
                );
            }

            Object maximo =
                    jc.getClientProperty(
                            CHAVE_MAXIMO_ORIGINAL
                    );

            if (maximo instanceof Dimension) {
                jc.setMaximumSize(
                        escalarDimension(
                                (Dimension) maximo,
                                escala
                        )
                );
            }
        }

        if (componente instanceof Container) {
            Component[] filhos =
                    ((Container) componente)
                            .getComponents();

            for (Component filho : filhos) {
                escalarComponente(
                        filho,
                        escala
                );
            }
        }
    }

    private void restaurarEscalaTelaCheia() {
        restaurarComponente(
                getContentPane()
        );
    }

    private void restaurarComponente(
            Component componente
    ) {
        if (componente instanceof JComponent) {
            JComponent jc =
                    (JComponent) componente;

            Object fonteOriginal =
                    jc.getClientProperty(
                            CHAVE_FONTE_ORIGINAL
                    );

            if (fonteOriginal instanceof Font) {
                jc.setFont(
                        (Font) fonteOriginal
                );
            }

            Object preferido =
                    jc.getClientProperty(
                            CHAVE_PREFERIDO_ORIGINAL
                    );

            if (preferido instanceof Dimension) {
                jc.setPreferredSize(
                        (Dimension) preferido
                );
            }

            Object minimo =
                    jc.getClientProperty(
                            CHAVE_MINIMO_ORIGINAL
                    );

            if (minimo instanceof Dimension) {
                jc.setMinimumSize(
                        (Dimension) minimo
                );
            }

            Object maximo =
                    jc.getClientProperty(
                            CHAVE_MAXIMO_ORIGINAL
                    );

            if (maximo instanceof Dimension) {
                jc.setMaximumSize(
                        (Dimension) maximo
                );
            }
        }

        if (componente instanceof Container) {
            Component[] filhos =
                    ((Container) componente)
                            .getComponents();

            for (Component filho : filhos) {
                restaurarComponente(
                        filho
                );
            }
        }
    }

    private Dimension escalarDimension(
            Dimension dimension,
            double escala
    ) {
        return new Dimension(
                Math.max(
                        1,
                        (int) Math.round(
                                dimension.width * escala
                        )
                ),
                Math.max(
                        1,
                        (int) Math.round(
                                dimension.height * escala
                        )
                )
        );
    }

    private void atualizarTextoBotaoTelaCheia() {
        JButton botao =
                localizarBotaoTelaCheia(
                        getContentPane()
                );

        if (botao != null) {
            botao.setText(
                    telaCheia
                            ? "Sair da tela cheia"
                            : "Tela cheia"
            );
        }
    }

    private JButton localizarBotaoTelaCheia(
            Component componente
    ) {
        if (componente instanceof JButton) {
            JButton botao =
                    (JButton) componente;

            if (Boolean.TRUE.equals(
                    botao.getClientProperty(
                            "botaoTelaCheia"
                    )
            )) {
                return botao;
            }
        }

        if (componente instanceof Container) {
            Component[] filhos =
                    ((Container) componente)
                            .getComponents();

            for (Component filho : filhos) {
                JButton resultado =
                        localizarBotaoTelaCheia(
                                filho
                        );

                if (resultado != null) {
                    return resultado;
                }
            }
        }

        return null;
    }

    // ================================================================
    // Comunicação com o Controller
    // ================================================================

    public void setOuvinte(
            AcoesJogador ouvinte
    ) {
        this.ouvinte = ouvinte;
    }

    // ================================================================
    // Tema
    // ================================================================

    /**
     * Detecta se o sistema está usando uma aparência clara.
     */
    private boolean detectarTemaClaroDoSistema() {
        try {
            Color cor =
                    UIManager.getColor(
                            "Panel.background"
                    );

            if (cor == null) {
                return false;
            }

            int luminosidade =
                    cor.getRed()
                            + cor.getGreen()
                            + cor.getBlue();

            return luminosidade > 500;
        } catch (Exception ex) {
            return false;
        }
    }

    public void aplicarTemaSelecionado() {
        String temaFundo =
                comboTemaFundo == null
                        ? null
                        : (String)
                        comboTemaFundo.getSelectedItem();

        String temaVisual =
                comboTemaVisual == null
                        ? null
                        : (String)
                        comboTemaVisual.getSelectedItem();

        String temaTabuleiro =
                comboTemaTabuleiro == null
                        ? null
                        : (String)
                        comboTemaTabuleiro.getSelectedItem();

        if (temaFundo == null) {
            temaFundo =
                    detectarTemaClaroDoSistema()
                            ? "Claro"
                            : "Escuro";
        }

        if (temaVisual == null) {
            temaVisual =
                    "Padrao";
        }

        if (temaTabuleiro == null) {
            temaTabuleiro =
                    "Padrao";
        }

        aplicarTema(
                temaFundo,
                temaVisual,
                temaTabuleiro
        );
    }

    private void aplicarTema(
            String temaFundo,
            String temaVisual,
            String temaTabuleiro
    ) {
        boolean claro =
                "Claro".equalsIgnoreCase(
                        temaFundo
                );

        boolean cyberpunk =
                temaVisual != null
                        && temaVisual.toLowerCase()
                        .contains("cyberpunk");

        boolean terminal =
                temaVisual != null
                        && temaVisual.toLowerCase()
                        .contains("terminal");

        if (cyberpunk) {
            if (claro) {
                corFundo =
                        new Color(245, 242, 250);

                corFundoClaro =
                        new Color(255, 248, 255);

                corDestaque =
                        CYBER_DESTAQUE;

                corTextoPrincipal =
                        new Color(45, 25, 55);

                corTextoSecundario =
                        new Color(105, 80, 120);

                corCard =
                        new Color(255, 248, 255);

                corCardHover =
                        new Color(245, 230, 250);

                corBorda =
                        new Color(175, 90, 180);

                corCelulaOculta =
                        new Color(232, 220, 238);

                corCelulaOcultaHover =
                        new Color(215, 195, 225);

                corBordaOculta =
                        new Color(165, 120, 180);

                corCelulaRevelada =
                        new Color(250, 245, 252);

                corBordaRevelada =
                        new Color(205, 175, 215);

                corTextoSobreRevelada =
                        new Color(50, 25, 60);

                corMinaFundo =
                        new Color(255, 215, 235);

                corMina =
                        CYBER_ROSA;

                corVitoria =
                        new Color(20, 165, 100);

                corBandeira =
                        CYBER_ROSA;

                coresNumeros =
                        new Color[]{
                                new Color(20, 140, 180),
                                new Color(25, 150, 75),
                                new Color(210, 35, 95),
                                new Color(130, 50, 180),
                                new Color(205, 100, 15),
                                new Color(20, 145, 140),
                                new Color(205, 30, 145),
                                new Color(80, 70, 90)
                        };
            } else {
                corFundo =
                        CYBER_FUNDO;

                corFundoClaro =
                        CYBER_FUNDO_CLARO;

                corDestaque =
                        CYBER_DESTAQUE;

                corTextoPrincipal =
                        CYBER_TEXTO;

                corTextoSecundario =
                        CYBER_TEXTO_SECUNDARIO;

                corCard =
                        CYBER_CARD;

                corCardHover =
                        CYBER_CARD_HOVER;

                corBorda =
                        CYBER_BORDA;

                corCelulaOculta =
                        CYBER_CELULA;

                corCelulaOcultaHover =
                        CYBER_CELULA_HOVER;

                corBordaOculta =
                        CYBER_BORDA_CELULA;

                corCelulaRevelada =
                        CYBER_REVELADA;

                corBordaRevelada =
                        CYBER_BORDA_REVELADA;

                corTextoSobreRevelada =
                        CYBER_TEXTO;

                corMinaFundo =
                        CYBER_MINA_FUNDO;

                corMina =
                        CYBER_MINA;

                corVitoria =
                        CYBER_VITORIA;

                corBandeira =
                        CYBER_BANDEIRA;

                coresNumeros =
                        new Color[]{
                                new Color(40, 200, 255),
                                new Color(70, 255, 110),
                                new Color(255, 70, 100),
                                new Color(200, 100, 255),
                                new Color(255, 160, 70),
                                new Color(70, 240, 220),
                                new Color(255, 100, 200),
                                new Color(220, 220, 235)
                        };
            }
        } else if (terminal) {
            if (claro) {
                corFundo =
                        new Color(235, 245, 235);

                corFundoClaro =
                        new Color(248, 255, 248);

                corDestaque =
                        new Color(20, 130, 35);

                corTextoPrincipal =
                        new Color(15, 65, 20);

                corTextoSecundario =
                        new Color(55, 105, 60);

                corCard =
                        new Color(248, 255, 248);

                corCardHover =
                        new Color(225, 240, 225);

                corBorda =
                        new Color(100, 145, 105);

                corCelulaOculta =
                        new Color(215, 232, 215);

                corCelulaOcultaHover =
                        new Color(195, 218, 195);

                corBordaOculta =
                        new Color(115, 155, 115);

                corCelulaRevelada =
                        new Color(245, 252, 245);

                corBordaRevelada =
                        new Color(190, 210, 190);

                corTextoSobreRevelada =
                        new Color(20, 70, 25);

                corMinaFundo =
                        new Color(250, 210, 210);

                corMina =
                        new Color(175, 35, 35);

                corVitoria =
                        new Color(20, 130, 40);

                corBandeira =
                        new Color(35, 120, 40);

                coresNumeros =
                        new Color[]{
                                new Color(20, 95, 150),
                                new Color(20, 125, 45),
                                new Color(180, 35, 40),
                                new Color(90, 50, 145),
                                new Color(165, 90, 15),
                                new Color(20, 110, 100),
                                new Color(155, 40, 105),
                                new Color(70, 80, 70)
                        };
            } else {
                corFundo =
                        TERMINAL_FUNDO;

                corFundoClaro =
                        TERMINAL_FUNDO_CLARO;

                corDestaque =
                        TERMINAL_DESTAQUE;

                corTextoPrincipal =
                        TERMINAL_TEXTO;

                corTextoSecundario =
                        TERMINAL_TEXTO_SECUNDARIO;

                corCard =
                        TERMINAL_CARD;

                corCardHover =
                        TERMINAL_CARD_HOVER;

                corBorda =
                        TERMINAL_BORDA;

                corCelulaOculta =
                        TERMINAL_CELULA;

                corCelulaOcultaHover =
                        TERMINAL_CELULA_HOVER;

                corBordaOculta =
                        TERMINAL_BORDA_CELULA;

                corCelulaRevelada =
                        TERMINAL_REVELADA;

                corBordaRevelada =
                        TERMINAL_BORDA_REVELADA;

                corTextoSobreRevelada =
                        TERMINAL_TEXTO;

                corMinaFundo =
                        TERMINAL_MINA_FUNDO;

                corMina =
                        TERMINAL_MINA;

                corVitoria =
                        TERMINAL_VITORIA;

                corBandeira =
                        TERMINAL_BANDEIRA;

                coresNumeros =
                        new Color[]{
                                new Color(80, 190, 255),
                                new Color(90, 255, 90),
                                new Color(255, 90, 90),
                                new Color(180, 100, 255),
                                new Color(255, 170, 70),
                                new Color(70, 240, 210),
                                new Color(255, 100, 200),
                                new Color(190, 230, 190)
                        };
            }

            fonteCelula =
                    FONTE_TERMINAL_CELULA;

            fonteTitulo =
                    FONTE_TERMINAL_TITULO;

            fonteSubtitulo =
                    FONTE_TERMINAL_SUBTITULO;

            fonteNormal =
                    FONTE_TERMINAL_NORMAL;

            fonteNumero =
                    FONTE_TERMINAL_NUMERO;

            fontePequena =
                    FONTE_TERMINAL_PEQUENA;
        } else {
            if (claro) {
                corFundo =
                        COR_FUNDO_CLARO_TEMA;

                corFundoClaro =
                        Color.WHITE;

                corDestaque =
                        new Color(20, 150, 145);

                corTextoPrincipal =
                        COR_TEXTO_PRINCIPAL_CLARO_TEMA;

                corTextoSecundario =
                        COR_TEXTO_SECUNDARIO_CLARO_TEMA;

                corCard =
                        COR_CARD_CLARO_TEMA;

                corCardHover =
                        COR_CARD_HOVER_CLARO_TEMA;

                corBorda =
                        COR_BORDA_CLARO_TEMA;

                corCelulaOculta =
                        COR_CELULA_OCULTA_CLARO_TEMA;

                corCelulaOcultaHover =
                        COR_CELULA_OCULTA_HOVER_CLARO_TEMA;

                corBordaOculta =
                        COR_BORDA_OCULTA_CLARO_TEMA;

                corCelulaRevelada =
                        COR_CELULA_REVELADA_CLARO_TEMA;

                corBordaRevelada =
                        COR_BORDA_REVELADA_CLARO_TEMA;

                corTextoSobreRevelada =
                        COR_TEXTO_SOBRE_REVELADA_CLARO_TEMA;

                corMinaFundo =
                        COR_MINA_FUNDO_CLARO_TEMA;

                corMina =
                        COR_MINA_CLARO_TEMA;

                corVitoria =
                        COR_VITORIA_CLARO_TEMA;

                corBandeira =
                        COR_BANDEIRA_CLARO_TEMA;

                coresNumeros =
                        CORES_NUMEROS_CLARO_TEMA;
            } else {
                corFundo =
                        COR_FUNDO;

                corFundoClaro =
                        COR_FUNDO_CLARO;

                corDestaque =
                        COR_DESTAQUE;

                corTextoPrincipal =
                        COR_TEXTO_PRINCIPAL;

                corTextoSecundario =
                        COR_TEXTO_SECUNDARIO;

                corCard =
                        COR_CARD;

                corCardHover =
                        COR_CARD_HOVER;

                corBorda =
                        COR_BORDA;

                corCelulaOculta =
                        COR_CELULA_OCULTA;

                corCelulaOcultaHover =
                        COR_CELULA_OCULTA_HOVER;

                corBordaOculta =
                        COR_BORDA_OCULTA;

                corCelulaRevelada =
                        COR_CELULA_REVELADA;

                corBordaRevelada =
                        COR_BORDA_REVELADA;

                corTextoSobreRevelada =
                        COR_TEXTO_SOBRE_REVELADA;

                corMinaFundo =
                        COR_MINA_FUNDO;

                corMina =
                        COR_MINA;

                corVitoria =
                        COR_VITORIA;

                corBandeira =
                        COR_BANDEIRA;

                coresNumeros =
                        CORES_NUMEROS;
            }

            fonteCelula =
                    FONTE_CELULA;

            fonteTitulo =
                    FONTE_TITULO;

            fonteSubtitulo =
                    FONTE_SUBTITULO;

            fonteNormal =
                    FONTE_NORMAL;

            fonteNumero =
                    FONTE_NUMERO;

            fontePequena =
                    FONTE_PEQUENA;
        }

        aplicarTemaTabuleiro(
                temaTabuleiro
        );

        if (painelPrincipal != null) {
            atualizarCoresRecursivamente(
                    painelPrincipal
            );
        }

        revalidate();
        repaint();
    }

    private void aplicarTemaTabuleiro(
            String temaTabuleiro
    ) {
        if (temaTabuleiro == null) {
            return;
        }

        String normalizado =
                temaTabuleiro.toLowerCase();

        if (normalizado.contains("escuro")) {
            corCelulaOculta =
                    new Color(45, 45, 58);

            corCelulaOcultaHover =
                    new Color(65, 65, 82);

            corBordaOculta =
                    new Color(85, 85, 105);

            corCelulaRevelada =
                    new Color(28, 28, 36);

            corBordaRevelada =
                    new Color(65, 65, 78);
        } else if (normalizado.contains("claro")) {
            corCelulaOculta =
                    new Color(222, 227, 235);

            corCelulaOcultaHover =
                    new Color(205, 212, 223);

            corBordaOculta =
                    new Color(170, 178, 190);

            corCelulaRevelada =
                    new Color(250, 250, 252);

            corBordaRevelada =
                    new Color(205, 210, 218);
        }
    }

    private void atualizarCoresRecursivamente(
            Component componente
    ) {
        if (componente instanceof JPanel) {
            JPanel painel =
                    (JPanel) componente;

            painel.setBackground(
                    corFundo
            );
        }

        if (componente instanceof JLabel) {
            JLabel label =
                    (JLabel) componente;

            label.setForeground(
                    corTextoPrincipal
            );

            if (label.getFont() != null) {
                label.setFont(
                        fonteCorrespondente(
                                label.getFont()
                        )
                );
            }
        }

        if (componente instanceof JButton) {
            JButton botao =
                    (JButton) componente;

            botao.setForeground(
                    corTextoPrincipal
            );

            botao.setBackground(
                    corFundoClaro
            );
        }

        if (componente instanceof JComboBox) {
            JComboBox<?> combo =
                    (JComboBox<?>) componente;

            combo.setForeground(
                    corTextoPrincipal
            );

            combo.setBackground(
                    corFundoClaro
            );
        }

        if (componente instanceof Container) {
            for (Component filho :
                    ((Container) componente)
                            .getComponents()) {

                atualizarCoresRecursivamente(
                        filho
                );
            }
        }
    }

    private Font fonteCorrespondente(
            Font fonteAtual
    ) {
        if (fonteAtual == null) {
            return fonteNormal;
        }

        int tamanho =
                Math.max(
                        1,
                        fonteAtual.getSize()
                );

        return fonteAtual.deriveFont(
                fonteAtual.getStyle(),
                tamanho
        );
    }

    // ================================================================
    // Tela inicial
    // ================================================================

    public void mostrarTelaInicial() {
        mostrarTelaInicial(false);
    }

    public void mostrarTelaInicial(boolean existePartidaSalva) {
        getContentPane().removeAll();

        painelPrincipal =
                new JPanel(
                        new BorderLayout()
                );

        painelPrincipal.setBackground(
                corFundo
        );

        JPanel conteudo =
                new JPanel();

        conteudo.setLayout(
                new BoxLayout(
                        conteudo,
                        BoxLayout.Y_AXIS
                )
        );

        conteudo.setBackground(
                corFundo
        );

        conteudo.setBorder(
                BorderFactory.createEmptyBorder(
                        35,
                        45,
                        35,
                        45
                )
        );

        JLabel titulo =
                new JLabel(
                        EMOJI_BOMBA
                                + " CAMPO MINADO"
                );

        titulo.setFont(
                fonteTitulo
        );

        titulo.setForeground(
                corDestaque
        );

        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        conteudo.add(
                titulo
        );

        conteudo.add(
                Box.createVerticalStrut(
                        10
                )
        );

        JLabel subtitulo =
                new JLabel(
                        "Escolha a dificuldade e configure sua partida"
                );

        subtitulo.setFont(
                fonteNormal
        );

        subtitulo.setForeground(
                corTextoSecundario
        );

        subtitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        conteudo.add(
                subtitulo
        );

        conteudo.add(
                Box.createVerticalStrut(
                        30
                )
        );

        JPanel painelConfiguracoes =
                criarPainelConfiguracoes();

        painelConfiguracoes.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        conteudo.add(
                painelConfiguracoes
        );

        conteudo.add(
                Box.createVerticalStrut(
                        25
                )
        );

        JButton iniciar =
                criarBotaoPrincipal(
                        "Iniciar jogo"
                );

        iniciar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        iniciar.addActionListener(
                e -> iniciarJogoSelecionado()
        );

        conteudo.add(
                iniciar
        );

        conteudo.add(
                Box.createVerticalStrut(
                        12
                )
        );

        JButton tutorial =
                criarBotaoSecundario(
                        "Como jogar"
                );

        tutorial.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        tutorial.addActionListener(
                e -> mostrarTutorial()
        );

        conteudo.add(
                tutorial
        );

        conteudo.add(
        Box.createVerticalStrut(
                12
        )
);

botaoTop5 =
        criarBotaoSecundario(
                "🏆 Ver Top 5"
        );

botaoTop5.setAlignmentX(
        Component.CENTER_ALIGNMENT
);

botaoTop5.addActionListener(
        e -> {
            if (ouvinte != null) {
                ouvinte.aoMostrarTop5();
            }
        }
);

conteudo.add(
        botaoTop5
);

        conteudo.add(
                Box.createVerticalStrut(
                        12
                )
        );

        JButton botaoContinuar =
                criarBotaoSecundario(
                        "▶ Continuar jogo"
                );

        botaoContinuar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        botaoContinuar.setEnabled(
                existePartidaSalva
        );

        botaoContinuar.addActionListener(
                e -> {
                    if (ouvinte != null) {
                        ouvinte.aoContinuarJogo();
                    }
                }
        );

        conteudo.add(
                botaoContinuar
        );

        painelPrincipal.add(
                conteudo,
                BorderLayout.CENTER
        );

        getContentPane().setLayout(
                new BorderLayout()
        );

        getContentPane().add(
                painelPrincipal,
                BorderLayout.CENTER
        );

        setResizable(false);

        pack();

        ajustarTamanhoJanelaAoMonitor();

        setLocationRelativeTo(null);

        revalidate();
        repaint();
    }

    private JPanel criarPainelConfiguracoes() {
        JPanel painel =
                new JPanel(
                        new GridBagLayout()
                );

        painel.setBackground(
                corCard
        );

        painel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corBorda
                        ),
                        BorderFactory.createEmptyBorder(
                                20,
                                25,
                                20,
                                25
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        8,
                        8,
                        8,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        JLabel dificuldade =
                criarLabelConfiguracao(
                        "Dificuldade"
                );

        comboTemaTabuleiro =
                criarComboBox(
                        new String[]{
                                "Tabuleiro Padrao",
                                "Tabuleiro Claro",
                                "Tabuleiro Escuro"
                        }
                );

        comboTemaFundo =
                criarComboBox(
                        new String[]{
                                detectarTemaClaroDoSistema()
                                        ? "Claro"
                                        : "Escuro",
                                detectarTemaClaroDoSistema()
                                        ? "Escuro"
                                        : "Claro"
                        }
                );

        comboTemaVisual =
                criarComboBox(
                        new String[]{
                                "Padrao",
                                "Cyberpunk Neon",
                                "Terminal Retro"
                        }
                );

        comboTempo =
                criarComboBox(
                        new String[]{
                                "Sem limite",
                                "30 segundos",
                                "60 segundos",
                                "120 segundos",
                                "300 segundos"
                        }
                );

        JComboBox<String> comboDificuldade =
                criarComboBox(
                        new String[]{
                                "Iniciante - 9 x 9 - 10 minas",
                                "Intermediario - 16 x 16 - 40 minas",
                                "Avancado - 16 x 30 - 99 minas"
                        }
                );

        adicionarLinhaConfiguracao(
                painel,
                gbc,
                0,
                "Dificuldade",
                comboDificuldade
        );

        adicionarLinhaConfiguracao(
                painel,
                gbc,
                1,
                "Tema de fundo",
                comboTemaFundo
        );

        adicionarLinhaConfiguracao(
                painel,
                gbc,
                2,
                "Tema visual",
                comboTemaVisual
        );

        adicionarLinhaConfiguracao(
                painel,
                gbc,
                3,
                "Tema do tabuleiro",
                comboTemaTabuleiro
        );

        adicionarLinhaConfiguracao(
                painel,
                gbc,
                4,
                "Tempo",
                comboTempo
        );

                comboDificuldade.putClientProperty(
                "dificuldade",
                true
        );

        painel.putClientProperty(
                "comboDificuldade",
                comboDificuldade
        );

        return painel;
    }


    public int getTempoLimiteSegundosSelecionado() {
        String selecionado = comboTempo.getSelectedItem().toString();

        switch (selecionado) {
            case "Sem limite":
                return 0;

            case "30 segundos":
                return 30;

            case "60 segundos":
                return 60;

            case "120 segundos":
                return 120;

            case "300 segundos":
                return 300;

            default:
                return 0;
        }
    }
    

    private void adicionarLinhaConfiguracao(
            JPanel painel,
            GridBagConstraints gbc,
            int linha,
            String texto,
            JComboBox<String> combo
    ) {
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.weightx = 0.35;

        JLabel label =
                criarLabelConfiguracao(
                        texto
                );

        painel.add(
                label,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 0.65;

        painel.add(
                combo,
                gbc
        );
    }

    private JLabel criarLabelConfiguracao(
            String texto
    ) {
        JLabel label =
                new JLabel(
                        texto
                );

        label.setFont(
                fonteNormal
        );

        label.setForeground(
                corTextoPrincipal
        );

        return label;
    }

    private JComboBox<String> criarComboBox(
            String[] itens
    ) {
        JComboBox<String> combo =
                new JComboBox<>(
                        itens
                );

        combo.setFont(
                fonteNormal
        );

        combo.setForeground(
                corTextoPrincipal
        );

        combo.setBackground(
                corFundoClaro
        );

        combo.setFocusable(false);

        combo.setBorder(
                BorderFactory.createLineBorder(
                        corBorda
                )
        );

        combo.setPreferredSize(
                new Dimension(
                        260,
                        34
                )
        );

        return combo;
    }

    private JButton criarBotaoPrincipal(
            String texto
    ) {
        JButton botao =
                new JButton(
                        texto
                );

        botao.setFont(
                fonteNormal
        );

        botao.setForeground(
                corFundo
        );

        botao.setBackground(
                corDestaque
        );

        botao.setFocusPainted(
                false
        );

        botao.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        24,
                        10,
                        24
                )
        );

        botao.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return botao;
    }

    private JButton criarBotaoSecundario(
            String texto
    ) {
        JButton botao =
                new JButton(
                        texto
                );

        botao.setFont(
                fonteNormal
        );

        botao.setForeground(
                corTextoPrincipal
        );

        botao.setBackground(
                corFundoClaro
        );

        botao.setFocusPainted(
                false
        );

        botao.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corBorda
                        ),
                        BorderFactory.createEmptyBorder(
                                9,
                                20,
                                9,
                                20
                        )
                )
        );

        botao.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return botao;
    }

    private void iniciarJogoSelecionado() {
        JPanel configuracoes =
                encontrarPainelConfiguracoes();

        if (configuracoes == null) {
            return;
        }

        JComboBox<?> comboDificuldade =
                (JComboBox<?>)
                        configuracoes.getClientProperty(
                                "comboDificuldade"
                        );

        if (comboDificuldade == null) {
            return;
        }

        int linhas = 9;
        int colunas = 9;
        int minas = 10;

        int selecionado =
                comboDificuldade.getSelectedIndex();

        if (selecionado == 1) {
            linhas = 16;
            colunas = 16;
            minas = 40;
        } else if (selecionado == 2) {
            linhas = 16;
            colunas = 30;
            minas = 99;
        }

        if (ouvinte != null) {
            ouvinte.aoEscolherDificuldade(
                    linhas,
                    colunas,
                    minas
            );
        }
    }

    private JPanel encontrarPainelConfiguracoes() {
        return encontrarPainelConfiguracoesRecursivo(
                getContentPane()
        );
    }

    private JPanel encontrarPainelConfiguracoesRecursivo(
            Component componente
    ) {
        if (componente instanceof JPanel) {
            JPanel painel =
                    (JPanel) componente;

            if (painel.getClientProperty(
                    "comboDificuldade"
            ) != null) {
                return painel;
            }
        }

        if (componente instanceof Container) {
            for (Component filho :
                    ((Container) componente)
                            .getComponents()) {

                JPanel resultado =
                        encontrarPainelConfiguracoesRecursivo(
                                filho
                        );

                if (resultado != null) {
                    return resultado;
                }
            }
        }

        return null;
    }

    private void mostrarTutorial() {
        JOptionPane.showMessageDialog(
                this,
                "<html>"
                        + "<div style='width:420px'>"
                        + "<h2>Como jogar</h2>"
                        + "<ol>"
                        + "<li>Escolha a dificuldade e o tempo.</li>"
                        + "<li>Clique com o botão esquerdo para revelar uma célula.</li>"
                        + "<li>Clique com o botão direito para colocar ou retirar uma bandeira.</li>"
                        + "<li>O objetivo é revelar todas as células que não possuem minas.</li>"
                        + "<li>Se explodir uma mina, o jogo segue conforme as vidas restantes.</li>"
                        + "<li>Se o tempo limite for atingido, a partida termina em derrota.</li>"
                        + "<li>Use o botão <b>Dica</b> para destacar uma célula segura (até 3 vezes por partida).</li>"
                        + "<li>Use F11 ou o botão <b>Tela cheia</b> para alternar a visualização.</li>"
                        + "</ol>"
                        + "</div>"
                        + "</html>",
                "Como jogar",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
public void mostrarTop5(
        java.util.List<Recordes.Registro> top5Iniciante,
        java.util.List<Recordes.Registro> top5Intermediario,
        java.util.List<Recordes.Registro> top5Avancado
) {

    JDialog dialog =
            new JDialog(
                    this,
                    "🏆 Top 5 - Melhores Tempos",
                    true
            );

    dialog.setSize(
            850,
            400
    );

    dialog.setLocationRelativeTo(
            this
    );

    dialog.setResizable(
            true
    );

    JPanel painelPrincipalTop5 =
            new JPanel(
                    new BorderLayout(
                            10,
                            10
                    )
            );

    painelPrincipalTop5.setBorder(
            BorderFactory.createEmptyBorder(
                    15,
                    15,
                    15,
                    15
            )
    );

    painelPrincipalTop5.setBackground(
            corFundo
    );

    JLabel titulo =
            new JLabel(
                    "🏆 TOP 5 - MELHORES TEMPOS",
                    SwingConstants.CENTER
            );

    titulo.setFont(
            fonteTitulo
    );

    titulo.setForeground(
            corDestaque
    );

    painelPrincipalTop5.add(
            titulo,
            BorderLayout.NORTH
    );

    JPanel painelColunas =
            new JPanel(
                    new GridLayout(
                            1,
                            3,
                            15,
                            0
                    )
            );

    painelColunas.setBackground(
            corFundo
    );

    painelColunas.add(
            criarColunaTop5(
                    "Iniciante",
                    top5Iniciante
            )
    );

    painelColunas.add(
            criarColunaTop5(
                    "Intermediário",
                    top5Intermediario
            )
    );

    painelColunas.add(
            criarColunaTop5(
                    "Avançado",
                    top5Avancado
            )
    );

    painelPrincipalTop5.add(
            painelColunas,
            BorderLayout.CENTER
    );

    JButton botaoFechar =
            criarBotaoSecundario(
                    "Fechar"
            );

    botaoFechar.addActionListener(
            e -> dialog.dispose()
    );

    JPanel painelBotao =
            new JPanel(
                    new FlowLayout(
                            FlowLayout.CENTER
                    )
            );

    painelBotao.setBackground(
            corFundo
    );

    painelBotao.add(
            botaoFechar
    );

    painelPrincipalTop5.add(
            painelBotao,
            BorderLayout.SOUTH
    );

    dialog.setContentPane(
            painelPrincipalTop5
    );

    dialog.setVisible(
            true
    );
}
private JPanel criarColunaTop5(
        String titulo,
        java.util.List<Recordes.Registro> registros
) {

    JPanel painel =
            new JPanel(
                    new BorderLayout(
                            5,
                            5
                    )
            );

    painel.setBackground(
            corCard
    );

    painel.setBorder(
            BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(
                            corBorda
                    ),
                    titulo
            )
    );

    JPanel lista =
            new JPanel();

    lista.setLayout(
            new BoxLayout(
                    lista,
                    BoxLayout.Y_AXIS
            )
    );

    lista.setBackground(
            corCard
    );

    if (registros == null
            || registros.isEmpty()) {

        JLabel vazio =
                new JLabel(
                        "Nenhum recorde ainda."
                );

        vazio.setFont(
                fontePequena
        );

        vazio.setForeground(
                corTextoSecundario
        );

        vazio.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lista.add(
                vazio
        );

    } else {

        for (int i = 0;
             i < registros.size();
             i++) {

            Recordes.Registro registro =
                    registros.get(i);

            JLabel jogador =
                    new JLabel(
                            (i + 1)
                                    + ". "
                                    + registro.getNome()
                                    + " - "
                                    + registro.getTempoSegundos()
                                    + " s"
                    );

            jogador.setFont(
                    fontePequena
            );

            jogador.setForeground(
                    corTextoPrincipal
            );

            jogador.setAlignmentX(
                    Component.CENTER_ALIGNMENT
            );

            jogador.setBorder(
                    BorderFactory.createEmptyBorder(
                            5,
                            5,
                            5,
                            5
                    )
            );

            lista.add(
                    jogador
            );
        }
    }

    painel.add(
            lista,
            BorderLayout.CENTER
    );

    return painel;
}

    // ================================================================
    // Tela de jogo
    // ================================================================

    /**
     * Monta a tela de jogo do zero para um tabuleiro de {@code linhas} x
     * {@code colunas}. Não recebe o {@link model.Tabuleiro}, apenas as
     * dimensões — quem decide o que cada célula mostra depois é sempre
     * o Controller, chamando {@link #atualizarCelula}.
     */
    public void iniciarTelaDeJogo(
            int linhas,
            int colunas,
            int totalMinas,
            int totalCelulas,
            int tempoLimiteSegundos,
            int vidasRestantes
    ) {
        getContentPane().removeAll();

        setLayout(
                new BorderLayout()
        );

        painelPrincipal =
                new JPanel(
                        new BorderLayout()
                );

        painelPrincipal.setBackground(
                corFundo
        );

        JPanel painelSuperior =
                criarPainelSuperior();

        painelPrincipal.add(
                painelSuperior,
                BorderLayout.NORTH
        );

        painelTabuleiro =
                criarPainelTabuleiro(
                        linhas,
                        colunas
                );

        // O tabuleiro ocupa diretamente a região central. Dessa forma,
        // o GridLayout pode reduzir ou ampliar proporcionalmente as células
        // conforme o espaço disponível, sem introduzir barras de rolagem.
        painelPrincipal.add(
                painelTabuleiro,
                BorderLayout.CENTER
        );

        JPanel painelEstatisticas =
                criarPainelEstatisticas(
                        totalMinas,
                        totalCelulas,
                        vidasRestantes
                );

        painelPrincipal.add(
                painelEstatisticas,
                BorderLayout.SOUTH
        );

        add(
                painelPrincipal,
                BorderLayout.CENTER
        );

        pack();

        ajustarTamanhoJanelaAoMonitor();

        setLocationRelativeTo(null);

        revalidate();
        repaint();
    }

    /**
     * Mantém a janela dentro da área útil do monitor. Como o tabuleiro está
     * diretamente no BorderLayout.CENTER, o GridLayout ajusta o tamanho das
     * células quando a janela precisa ser menor que o tamanho preferido.
     * Isso evita barras de rolagem sem deformar a quantidade de linhas ou
     * colunas do tabuleiro.
     */
    private void ajustarTamanhoJanelaAoMonitor() {
        GraphicsConfiguration configuracao =
                getGraphicsConfiguration();

        if (configuracao == null) {
            return;
        }

        Rectangle area =
                configuracao.getBounds();

        Insets insets =
                Toolkit.getDefaultToolkit()
                        .getScreenInsets(
                                configuracao
                        );

        int larguraDisponivel =
                Math.max(
                        1,
                        area.width
                                - insets.left
                                - insets.right
                );

        int alturaDisponivel =
                Math.max(
                        1,
                        area.height
                                - insets.top
                                - insets.bottom
                );

        int larguraMaxima =
                Math.max(
                        1,
                        (int)
                                (larguraDisponivel * 0.95)
                );

        int alturaMaxima =
                Math.max(
                        1,
                        (int)
                                (alturaDisponivel * 0.95)
                );

        Dimension tamanho =
                getSize();

        int largura =
                Math.min(
                        tamanho.width,
                        larguraMaxima
                );

        int altura =
                Math.min(
                        tamanho.height,
                        alturaMaxima
                );

        setSize(
                Math.max(
                        1,
                        largura
                ),
                Math.max(
                        1,
                        altura
                )
        );

        setMinimumSize(
                new Dimension(
                        Math.min(
                                getMinimumSize().width,
                                larguraMaxima
                        ),
                        Math.min(
                                getMinimumSize().height,
                                alturaMaxima
                        )
                )
        );
    }

    private JPanel criarPainelSuperior() {
        JPanel painel =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        painel.setBackground(
                corFundo
        );

        painel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        20,
                        10,
                        20
                )
        );

        labelStatus =
                new JLabel(
                        EMOJI_BOMBA
                                + " Campo Minado"
                );

        labelStatus.setFont(
                fonteSubtitulo
        );

        labelStatus.setForeground(
                corTextoPrincipal
        );

        painel.add(
                labelStatus,
                BorderLayout.WEST
        );

        lblTempo =
                new JLabel(
                        EMOJI_RELOGIO
                                + " 00:00"
                );

        lblTempo.setFont(
                fonteNumero
        );

        lblTempo.setForeground(
                corDestaque
        );

        lblTempo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        painel.add(
                lblTempo,
                BorderLayout.CENTER
        );

        JButton novoJogo =
                new JButton(
                        "Novo jogo"
                );

        novoJogo.setFont(
                fontePequena
        );

        novoJogo.setForeground(
                corTextoPrincipal
        );

        novoJogo.setBackground(
                corFundoClaro
        );

        novoJogo.setFocusPainted(
                false
        );

        novoJogo.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corBorda
                        ),
                        BorderFactory.createEmptyBorder(
                                7,
                                12,
                                7,
                                12
                        )
                )
        );

        novoJogo.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        novoJogo.addActionListener(
                e -> {
                    if (ouvinte != null) {
                        ouvinte.aoPedirNovoJogo();
                    }
                }
        );

        botaoDica =
                new JButton(
                        "Dica (3 restantes)"
                );

        botaoDica.setFont(
                fontePequena
        );

        botaoDica.setForeground(
                corTextoPrincipal
        );

        botaoDica.setBackground(
                corFundoClaro
        );

        botaoDica.setFocusPainted(
                false
        );

        botaoDica.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corBorda
                        ),
                        BorderFactory.createEmptyBorder(
                                7,
                                12,
                                7,
                                12
                        )
                )
        );

        botaoDica.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        botaoDica.addActionListener(
                e -> {
                    if (ouvinte != null) {
                        ouvinte.aoPedirDica();
                    }
                }
        );

        JButton telaCheiaBotao =
                new JButton(
                        telaCheia
                                ? "Sair da tela cheia"
                                : "Tela cheia"
                );

        telaCheiaBotao.putClientProperty(
                "botaoTelaCheia",
                true
        );

        telaCheiaBotao.setFont(
                fontePequena
        );

        telaCheiaBotao.setForeground(
                corTextoPrincipal
        );

        telaCheiaBotao.setBackground(
                corFundoClaro
        );

        telaCheiaBotao.setFocusPainted(
                false
        );

        telaCheiaBotao.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corBorda
                        ),
                        BorderFactory.createEmptyBorder(
                                7,
                                12,
                                7,
                                12
                        )
                )
        );

        telaCheiaBotao.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        telaCheiaBotao.addActionListener(
                e -> alternarTelaCheia()
        );

        JPanel botoes =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        botoes.setOpaque(
                false
        );

        botoes.add(
                botaoDica
        );

        JButton salvarJogo =
                new JButton(
                        "Salvar jogo"
                );

        salvarJogo.setFont(
                fontePequena
        );

        salvarJogo.setForeground(
                corTextoPrincipal
        );

        salvarJogo.setBackground(
                corFundoClaro
        );

        salvarJogo.setFocusPainted(
                false
        );

        salvarJogo.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corBorda
                        ),
                        BorderFactory.createEmptyBorder(
                                7,
                                12,
                                7,
                                12
                        )
                )
        );

        salvarJogo.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        salvarJogo.addActionListener(
                e -> {
                    if (ouvinte != null) {
                        ouvinte.aoSalvarJogo();
                    }
                }
        );

        botoes.add(
                salvarJogo
        );

        botoes.add(
                telaCheiaBotao
        );

        botoes.add(
                novoJogo
        );

        painel.add(
                botoes,
                BorderLayout.EAST
        );

        return painel;
    }

    private JPanel criarPainelEstatisticas(
            int totalMinas,
            int totalCelulas,
            int vidasRestantes
    ) {
        JPanel painel =
                new JPanel();

        painel.setLayout(
                new BoxLayout(
                        painel,
                        BoxLayout.Y_AXIS
                )
        );

        painel.setBackground(
                corFundo
        );

        painel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        20,
                        15,
                        20
                )
        );

        JPanel linha =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0
                        )
                );

        linha.setBackground(
                corFundo
        );

        JPanel itemMinas =
                criarItemEstatistica(
                        EMOJI_ICONE_ESTATISTICA
                                + " Minas",
                        String.valueOf(
                                totalMinas
                        )
                );

        JPanel itemReveladas =
                criarItemEstatistica(
                        "Células",
                        "0 / "
                                + totalCelulas
                );

        JPanel itemJogadas =
                criarItemEstatistica(
                        EMOJI_JOGADA
                                + " Jogadas",
                        "0"
                );

        JPanel itemVidas =
                criarItemEstatistica(
                        EMOJI_VIDA
                                + " Vidas",
                        String.valueOf(
                                Math.max(
                                        0,
                                        vidasRestantes
                                )
                        )
                );

        linha.add(
                itemMinas
        );

        linha.add(
                itemReveladas
        );

        linha.add(
                itemJogadas
        );

        linha.add(
                itemVidas
        );

        lblMinasRestantes =
                (JLabel)
                        itemMinas.getClientProperty(
                                "valor"
                        );

        lblCelulasReveladas =
                (JLabel)
                        itemReveladas.getClientProperty(
                                "valor"
                        );

        lblJogadas =
                (JLabel)
                        itemJogadas.getClientProperty(
                                "valor"
                        );

        lblVidas =
                (JLabel)
                        itemVidas.getClientProperty(
                                "valor"
                        );

        painel.add(
                linha
        );

        painel.add(
                Box.createVerticalStrut(
                        10
                )
        );

        barraProgresso =
                new JProgressBar(
                        0,
                        Math.max(
                                1,
                                totalCelulas
                        )
                );

        barraProgresso.setValue(
                0
        );

        barraProgresso.setStringPainted(
                true
        );

        barraProgresso.setString(
                "0%"
        );

        barraProgresso.setForeground(
                corDestaque
        );

        barraProgresso.setBackground(
                corFundo
        );

        barraProgresso.setBorder(
                BorderFactory.createLineBorder(
                        corBorda
                )
        );

        barraProgresso.setPreferredSize(
                new Dimension(
                        150,
                        20
                )
        );

        barraProgresso.setMaximumSize(
                new Dimension(
                        150,
                        20
                )
        );

        barraProgresso.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        painel.add(
                barraProgresso
        );

        painel.add(
                Box.createVerticalStrut(
                        15
                )
        );

        painel.add(
                Box.createVerticalGlue()
        );

        JLabel lblDica =
                new JLabel(
                        "<html><center>"
                                + "\uD83D\uDDB1\uFE0F Esquerdo: revelar<br>"
                                + "\uD83D\uDDB1\uFE0F Direito: bandeira"
                                + "</center></html>"
                );

        lblDica.setFont(
                fontePequena
        );

        lblDica.setForeground(
                corTextoSecundario
        );

        lblDica.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        painel.add(
                lblDica
        );

        return painel;
    }

    /**
     * Cria um item de estatística (título + valor) como um único painel,
     * guardando a referência ao label de valor via putClientProperty para
     * que possa ser atualizado depois. (Antes o valor era retornado
     * "solto", sem o painel-pai ser adicionado à tela — corrigido aqui.)
     */
    private JPanel criarItemEstatistica(
            String titulo,
            String valorInicial
    ) {
        JPanel painelItem =
                new JPanel();

        painelItem.setLayout(
                new BoxLayout(
                        painelItem,
                        BoxLayout.Y_AXIS
                )
        );

        painelItem.setBackground(
                corFundoClaro
        );

        painelItem.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel lblTitulo =
                new JLabel(
                        titulo
                );

        lblTitulo.setFont(
                fontePequena
        );

        lblTitulo.setForeground(
                corTextoSecundario
        );

        lblTitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel lblValor =
                new JLabel(
                        valorInicial
                );

        lblValor.setFont(
                fonteNumero
        );

        lblValor.setForeground(
                corTextoPrincipal
        );

        lblValor.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        painelItem.add(
                lblTitulo
        );

        painelItem.add(
                Box.createVerticalStrut(
                        4
                )
        );

        painelItem.add(
                lblValor
        );

        painelItem.putClientProperty(
                "valor",
                lblValor
        );

        painelItem.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corBorda
                        ),
                        BorderFactory.createEmptyBorder(
                                8,
                                10,
                                8,
                                10
                        )
                )
        );

        return painelItem;
    }

    // ================================================================
    // Tabuleiro
    // ================================================================

    private JPanel criarPainelTabuleiro(
            int linhas,
            int colunas
    ) {
        JPanel painel =
                new JPanel(
                        new GridLayout(
                                linhas,
                                colunas,
                                2,
                                2
                        )
                );
                
        painel.putClientProperty(
        "colunas",
        colunas
        );

        painel.setBackground(
                corFundo
        );

        painel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        for (int linha = 0;
             linha < linhas;
             linha++) {

            for (int coluna = 0;
                 coluna < colunas;
                 coluna++) {

                JButton botao =
                        criarBotaoCelula(
                                linha,
                                coluna
                        );

                painel.add(
                        botao
                );
            }
        }

        return painel;
    }

    private JButton criarBotaoCelula(
            int linha,
            int coluna
    ) {
        JButton botao =
                new JButton();

        botao.setFont(
                fonteCelula
        );

        botao.setFocusPainted(
                false
        );

        botao.setMargin(
                new Insets(
                        0,
                        0,
                        0,
                        0
                )
        );

        botao.setPreferredSize(
                new Dimension(
                        36,
                        36
                )
        );

        botao.setMinimumSize(
                new Dimension(
                        8,
                        8
                )
        );

        botao.setBackground(
                corCelulaOculta
        );

        botao.setForeground(
                corTextoPrincipal
        );

        botao.setBorder(
                BorderFactory.createLineBorder(
                        corBordaOculta
                )
        );

        botao.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        botao.putClientProperty(
                "linha",
                linha
        );

        botao.putClientProperty(
                "coluna",
                coluna
        );

        botao.putClientProperty(
                "revelada",
                false
        );

        botao.putClientProperty(
                "marcada",
                false
        );

        botao.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(
                            MouseEvent evento
                    ) {
                        boolean revelada =
                                Boolean.TRUE.equals(
                                        botao.getClientProperty(
                                                "revelada"
                                        )
                                );

                        boolean dica =
                                Boolean.TRUE.equals(
                                        botao.getClientProperty(
                                                "dica"
                                        )
                                );

                        if (!revelada && !dica) {
                            botao.setBackground(
                                    corCelulaOcultaHover
                            );
                        }
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent evento
                    ) {
                        boolean revelada =
                                Boolean.TRUE.equals(
                                        botao.getClientProperty(
                                                "revelada"
                                        )
                                );

                        boolean dica =
                                Boolean.TRUE.equals(
                                        botao.getClientProperty(
                                                "dica"
                                        )
                                );

                        if (!revelada && !dica) {
                            botao.setBackground(
                                    corCelulaOculta
                            );
                        }
                    }

                    @Override
                    public void mouseReleased(
                            MouseEvent evento
                    ) {
                        if (ouvinte == null) {
                            return;
                        }

                        if (SwingUtilities.isRightMouseButton(
                                evento
                        )) {
                            boolean marcada =
                                    Boolean.TRUE.equals(
                                            botao.getClientProperty(
                                                    "marcada"
                                            )
                                    );

                            /*
                             * Uma bandeira já existente sempre pode ser retirada.
                             * Uma nova bandeira somente é encaminhada ao Controller
                             * quando ainda existe pelo menos uma bandeira disponível.
                             *
                             * Essa proteção é feita antes de chamar o Controller,
                             * evitando que a interação normal da interface produza
                             * uma contagem negativa de bandeiras.
                             */
                            if (marcada
                                    || podeAdicionarBandeira()) {

                                ouvinte.aoMarcarCelula(
                                        linha,
                                        coluna
                                );
                            }

                        } else if (
                                SwingUtilities.isLeftMouseButton(
                                        evento
                                )
                        ) {
                            ouvinte.aoRevelarCelula(
                                    linha,
                                    coluna
                            );
                        }
                    }
                }
        );

        return botao;
    }

    /**
     * Verifica se ainda existe espaço para colocar uma nova bandeira.
     *
     * O valor exibido pelo Controller é usado como fonte de verdade visual.
     * Quando chega a zero, novas bandeiras são ignoradas.
     *
     * Uma bandeira já existente não passa por este método, pois precisa
     * continuar podendo ser retirada mesmo quando o contador estiver em zero.
     */
    private boolean podeAdicionarBandeira() {
        if (lblMinasRestantes == null) {
            return true;
        }

        try {
            int minasRestantes =
                    Integer.parseInt(
                            lblMinasRestantes
                                    .getText()
                                    .trim()
                    );

            return minasRestantes > 0;

        } catch (NumberFormatException ex) {
            return false;
        }
    }

    // ================================================================
    // Atualização das células
    // ================================================================

    public void atualizarDicas(
            int dicasUsadas,
            int limiteDicas
    ) {
        if (botaoDica == null) {
            return;
        }

        int dicasRestantes =
                Math.max(
                        0,
                        limiteDicas - dicasUsadas
                );

        botaoDica.setText(
                "Dica ("
                        + dicasRestantes
                        + " restantes)"
        );

        botaoDica.setEnabled(
                dicasRestantes > 0
        );
    }

    public void destacarCelulaDica(
            int linha,
            int coluna
    ) {
        if (painelTabuleiro == null) {
            return;
        }

        int indice =
                linha
                        * painelQuantidadeColunas()
                        + coluna;

        if (indice < 0
                || indice >= painelTabuleiro
                        .getComponentCount()) {
            return;
        }

        Component componente =
                painelTabuleiro.getComponent(
                        indice
                );

        if (!(componente instanceof JButton)) {
            return;
        }

        JButton botao =
                (JButton) componente;

        botao.putClientProperty(
                "dica",
                true
        );

        botao.setBackground(
                corDestaque
        );

        botao.setForeground(
                corFundo
        );

        botao.setBorder(
                BorderFactory.createLineBorder(
                        corDestaque,
                        3
                )
        );

        botao.repaint();
    }

    public void mostrarMensagemDica(
            String mensagem
    ) {
        if (labelStatus != null) {
            labelStatus.setText(
                    EMOJI_BOMBA
                            + " "
                            + mensagem
            );

            labelStatus.setForeground(
                    corDestaque
            );
        }
    }

    public void atualizarRecordes(
            int recordeIniciante,
            int recordeIntermediario,
            int recordeAvancado
    ) {
        if (lblRecordes == null) {
            return;
        }

        lblRecordes.setText(
                "<html><center>"
                        + "<b>🏆 Melhores tempos</b><br>"
                        + "Iniciante: "
                        + formatarRecorde(recordeIniciante)
                        + " &nbsp; | &nbsp; "
                        + "Intermediário: "
                        + formatarRecorde(recordeIntermediario)
                        + " &nbsp; | &nbsp; "
                        + "Avançado: "
                        + formatarRecorde(recordeAvancado)
                        + "</center></html>"
        );
    }
public void atualizarTop5(
        java.util.List<Recordes.Registro> top5Iniciante,
        java.util.List<Recordes.Registro> top5Intermediario,
        java.util.List<Recordes.Registro> top5Avancado
) {
    if (lblRecordes == null) {
        return;
    }

    StringBuilder html = new StringBuilder();

    html.append("<html><center>");
    html.append("<b>🏆 TOP 5 - MELHORES TEMPOS</b><br><br>");

    html.append("<b>Iniciante</b><br>");
    html.append(formatarTop5(top5Iniciante));
    html.append("<br>");

    html.append("<b>Intermediário</b><br>");
    html.append(formatarTop5(top5Intermediario));
    html.append("<br>");

    html.append("<b>Avançado</b><br>");
    html.append(formatarTop5(top5Avancado));

    html.append("</center></html>");

    lblRecordes.setText(html.toString());
}

private String formatarTop5(
        java.util.List<Recordes.Registro> registros
) {
    if (registros == null || registros.isEmpty()) {
        return "Nenhum recorde ainda.";
    }

    StringBuilder texto = new StringBuilder();

    for (int i = 0; i < registros.size(); i++) {

        Recordes.Registro registro = registros.get(i);

        texto.append(i + 1)
                .append(". ")
                .append(registro.getNome())
                .append(" - ")
                .append(registro.getTempoSegundos())
                .append(" s")
                .append("<br>");
    }

    return texto.toString();
}
    private String formatarRecorde(
            int segundos
    ) {
        if (segundos < 0) {
            return "—";
        }

        return segundos + " s";
    }

    public void atualizarCelula(
            int linha,
            int coluna,
            LeituraTabuleiro leitura
    ) {
        if (painelTabuleiro == null
                || leitura == null) {
            return;
        }

        int indice =
                linha
                        * painelQuantidadeColunas()
                        + coluna;

        if (indice < 0
                || indice >= painelTabuleiro
                        .getComponentCount()) {
            return;
        }

        Component componente =
                painelTabuleiro
                        .getComponent(
                                indice
                        );

        if (!(componente instanceof JButton)) {
            return;
        }

        JButton botao =
                (JButton) componente;

        botao.putClientProperty(
                "dica",
                false
        );

        boolean revelada =
                leitura.isRevelada(
                        linha,
                        coluna
                );

        boolean marcada =
                leitura.isMarcada(
                        linha,
                        coluna
                );

        boolean minada =
                leitura.isMinada(
                        linha,
                        coluna
                );

        int minasVizinhas =
                leitura.getMinasVizinhas(
                        linha,
                        coluna
                );

        botao.putClientProperty(
                "revelada",
                revelada
        );

        botao.putClientProperty(
                "marcada",
                marcada
        );

        if (minada && revelada) {
            botao.setText(
                    EMOJI_BOMBA
            );

            botao.setFont(
                    fonteCelula
            );

            botao.setForeground(
                    corMina
            );

            botao.setBackground(
                    corMinaFundo
            );

            botao.setBorder(
                    BorderFactory.createLineBorder(
                            corMina
                    )
            );

            return;
        }

        if (marcada) {
            botao.setText(
                    EMOJI_BANDEIRA
            );

            botao.setFont(
                    fonteCelula
            );

            botao.setForeground(
                    corBandeira
            );

            botao.setBackground(
                    corCelulaOculta
            );

            botao.setBorder(
                    BorderFactory.createLineBorder(
                            corBandeira
                    )
            );

            return;
        }

        if (!revelada) {
            botao.setText(
                    ""
            );

            botao.setFont(
                    fonteCelula
            );

            botao.setForeground(
                    corTextoPrincipal
            );

            botao.setBackground(
                    corCelulaOculta
            );

            botao.setBorder(
                    BorderFactory.createLineBorder(
                            corBordaOculta
                    )
            );

            return;
        }

        botao.setBackground(
                corCelulaRevelada
        );

        botao.setBorder(
                BorderFactory.createLineBorder(
                        corBordaRevelada
                )
        );

        if (minasVizinhas > 0) {
            botao.setText(
                    String.valueOf(
                            minasVizinhas
                    )
            );

            int indiceCor =
                    Math.min(
                            minasVizinhas,
                            coresNumeros.length
                    ) - 1;

            if (indiceCor >= 0) {
                botao.setForeground(
                        coresNumeros[
                                indiceCor
                        ]
                );
            } else {
                botao.setForeground(
                        corTextoSobreRevelada
                );
            }

        } else {
            botao.setText(
                    ""
            );

            botao.setForeground(
                    corTextoSobreRevelada
            );
        }
    }

    private int painelQuantidadeColunas() {
        if (painelTabuleiro == null) {
            return 1;
        }

        Object valor =
                painelTabuleiro.getClientProperty(
                        "colunas"
                );

        if (valor instanceof Integer) {
            return Math.max(
                    1,
                    (Integer) valor
            );
        }

        int quantidade =
                painelTabuleiro.getComponentCount();

        if (quantidade <= 0) {
            return 1;
        }

        for (Component componente :
                painelTabuleiro.getComponents()) {

            if (componente instanceof JButton) {
                Object coluna =
                        ((JButton) componente)
                                .getClientProperty(
                                        "coluna"
                                );

                if (coluna instanceof Integer) {
                    int c =
                            (Integer) coluna;

                    int maior =
                            c + 1;

                    painelTabuleiro.putClientProperty(
                            "colunas",
                            maior
                    );

                    return maior;
                }
            }
        }

        return 1;
    }

    // ================================================================
    // Estatísticas e tempo
    // ================================================================

    public void atualizarTempo(
            String texto
    ) {
        if (lblTempo != null) {
            lblTempo.setText(
                    texto
            );
        }
    }

    /**
     * Atualiza os indicadores de estatísticas recebidos diretamente do
     * Controller. A assinatura possui exatamente os cinco parâmetros
     * usados pelo {@code CampoMinadoController}.
     */
    public void atualizarEstatisticas(
            int minasRestantes,
            int celulasReveladas,
            int totalCelulas,
            int jogadas,
            int vidasRestantes
    ) {
        if (lblMinasRestantes != null) {
            /*
             * A View nunca exibe quantidade negativa de minas.
             * O Controller calcula o valor real e, em condições normais,
             * a proteção de bandeiras impede que ele fique negativo.
             */
            int minasRestantesExibidas =
                    Math.max(
                            0,
                            minasRestantes
                    );

            lblMinasRestantes.setText(
                    String.valueOf(
                            minasRestantesExibidas
                    )
            );

            lblMinasRestantes.setForeground(
                    corTextoPrincipal
            );
        }

        if (lblCelulasReveladas != null) {
            lblCelulasReveladas.setText(
                    celulasReveladas
                            + " / "
                            + totalCelulas
            );
        }

        if (lblJogadas != null) {
            lblJogadas.setText(
                    String.valueOf(
                            jogadas
                    )
            );
        }

        if (lblVidas != null) {
            int vidasExibidas =
                    Math.max(
                            0,
                            vidasRestantes
                    );

            lblVidas.setText(
                    String.valueOf(
                            vidasExibidas
                    )
            );

            if (vidasExibidas == 0) {
                lblVidas.setForeground(
                        corMina
                );
            } else {
                lblVidas.setForeground(
                        corVitoria
                );
            }
        }

        if (barraProgresso != null) {
            int progresso =
                    totalCelulas > 0
                            ? (int) (
                            (celulasReveladas
                                    * 100.0)
                                    / totalCelulas
                    )
                            : 0;

            progresso =
                    Math.max(
                            0,
                            Math.min(
                                    100,
                                    progresso
                            )
                    );

            barraProgresso.setValue(
                    Math.min(
                            celulasReveladas,
                            Math.max(
                                    1,
                                    totalCelulas
                            )
                    )
            );

            barraProgresso.setMaximum(
                    Math.max(
                            1,
                            totalCelulas
                    )
            );

            barraProgresso.setString(
                    progresso
                            + "%"
            );
        }
    }

    // ================================================================
// Resultado da partida
// ================================================================

/**
 * Solicita o nome do jogador quando ele consegue entrar
 * no Top 5 da dificuldade atual.
 *
 * @return nome informado pelo jogador ou "Jogador" caso
 *         o campo fique vazio ou a janela seja cancelada.
 */
public String solicitarNomeJogador() {

    String nome =
            JOptionPane.showInputDialog(
                    this,
                    "Parabéns! Você entrou no Top 5!\n"
                            + "Digite seu nome:",
                    "Novo recorde",
                    JOptionPane.PLAIN_MESSAGE
            );

    if (nome == null
            || nome.trim().isEmpty()) {

        return "Jogador";
    }

    return nome.trim();
}

public void mostrarMensagemSalvamento(
        String mensagem
) {
    JOptionPane.showMessageDialog(
            this,
            mensagem,
            "Salvar / Continuar jogo",
            JOptionPane.INFORMATION_MESSAGE
    );
}

public void mostrarDerrota() {
    if (labelStatus != null) {
        labelStatus.setText(
                EMOJI_BOMBA
                        + " Game Over"
        );

        labelStatus.setForeground(
                corMina
        );
    }
}

public void mostrarVitoria() {
    if (labelStatus != null) {
        labelStatus.setText(
                "✓ Vitória!"
        );

        labelStatus.setForeground(
                corVitoria
        );
    }
}

    public void piscarFundoDeExplosao(
            boolean ativo
    ) {
        Color cor =
                ativo
                        ? corMinaFundo
                        : corFundo;

        getContentPane().setBackground(
                cor
        );

        if (painelPrincipal != null) {
            painelPrincipal.setBackground(
                    cor
            );
        }

        repaint();
    }

    public void marcarMinaExplodida(
            int linha,
            int coluna
    ) {
        if (painelTabuleiro == null) {
            return;
        }

        int indice =
                linha
                        * painelQuantidadeColunas()
                        + coluna;

        if (indice < 0
                || indice >= painelTabuleiro
                        .getComponentCount()) {
            return;
        }

        Component componente =
                painelTabuleiro
                        .getComponent(
                                indice
                        );

        if (!(componente instanceof JButton)) {
            return;
        }

        JButton botao =
                (JButton) componente;

        botao.setText(
                EMOJI_BOMBA
        );

        botao.setForeground(
                corMina
        );

        botao.setBackground(
                corMinaFundo
        );

        botao.setBorder(
                BorderFactory.createLineBorder(
                        corMina
                )
        );

        botao.putClientProperty(
                "revelada",
                true
        );
    }

    public void destacarCelulaVencedora(
            int linha,
            int coluna
    ) {
        if (painelTabuleiro == null) {
            return;
        }

        int indice =
                linha
                        * painelQuantidadeColunas()
                        + coluna;

        if (indice < 0
                || indice >= painelTabuleiro
                        .getComponentCount()) {
            return;
        }

        Component componente =
                painelTabuleiro
                        .getComponent(
                                indice
                        );

        if (!(componente instanceof JButton)) {
            return;
        }

        JButton botao =
                (JButton) componente;

        botao.setBackground(
                corVitoria
        );

        botao.setForeground(
                corFundo
        );

        botao.setBorder(
                BorderFactory.createLineBorder(
                        corVitoria
                )
        );
    }
}