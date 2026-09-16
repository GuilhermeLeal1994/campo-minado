package view;

import controller.AcoesJogador;
import model.LeituraTabuleiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * VIEW da arquitetura MVC: cuida só de desenhar a tela e capturar
 * interações do usuário. Nunca decide o que um clique "significa" em
 * termos de regra de jogo — ela apenas repassa o clique para quem
 * implementa {@link AcoesJogador} (o Controller) e espera ser chamada de
 * volta para atualizar o que aparece na tela.
 */
public class CampoMinadoView extends JFrame {

    private static final Color COR_FUNDO =
            new Color(30, 30, 35);

    private static final Color COR_FUNDO_CLARO =
            new Color(45, 45, 52);

    private static final Color COR_DESTAQUE =
            new Color(70, 130, 180);

    private static final Color COR_CELULA_OCULTA =
            new Color(72, 78, 96);

    private static final Color COR_CELULA_OCULTA_HOVER =
            new Color(90, 97, 118);

    private static final Color COR_BORDA_OCULTA =
            new Color(100, 107, 128);

    private static final Color COR_CELULA_REVELADA =
            new Color(228, 228, 233);

    private static final Color COR_BORDA_REVELADA =
            new Color(195, 195, 202);

    private static final Color COR_TEXTO_SOBRE_REVELADA =
            new Color(40, 40, 45);

    private static final Color COR_MINA =
            new Color(220, 60, 60);

    private static final Color COR_MINA_FUNDO =
            new Color(60, 20, 20);

    private static final Color COR_VITORIA =
            new Color(50, 180, 80);

    private static final Color COR_TEXTO_PRINCIPAL =
            new Color(230, 230, 235);

    private static final Color COR_TEXTO_SECUNDARIO =
            new Color(150, 150, 160);

    private static final Color COR_BORDA =
            new Color(80, 80, 90);

    private static final Color COR_CARD =
            new Color(50, 50, 58);

    private static final Color COR_CARD_HOVER =
            new Color(65, 65, 78);

    private static final Color COR_BANDEIRA =
            new Color(230, 180, 50);

    private static final String[] TEMAS_FUNDO = {
            "Escuro",
            "Claro",
            "Campo"
    };

    private static final String[] TEMAS_VISUAIS = {
            "Padrão",
            "Cyberpunk Neon",
            "Terminal Retro"
    };

    private static final String[] TEMAS_TABULEIRO = {
            "Clássico",
            "Noite",
            "Verde"
    };

    private static final String[] TEMPOS_JOGO = {
            "Sem limite",
            "1 minuto",
            "2 minutos",
            "3 minutos",
            "5 minutos"
    };

    private static final Font FONTE_CELULA =
            new Font("Segoe UI Emoji", Font.BOLD, 20);

    private static final Font FONTE_TITULO =
            new Font("Segoe UI", Font.BOLD, 28);

    private static final Font FONTE_SUBTITULO =
            new Font("Segoe UI", Font.BOLD, 16);

    private static final Font FONTE_NORMAL =
            new Font("Segoe UI", Font.PLAIN, 14);

    private static final Font FONTE_NUMERO =
            new Font("Consolas", Font.BOLD, 18);

    private static final Font FONTE_PEQUENA =
            new Font("Segoe UI", Font.PLAIN, 12);

    private static final String EMOJI_BOMBA =
            "\uD83D\uDCA3";

    private static final String EMOJI_BANDEIRA =
            "\uD83D\uDEA9";

    private static final String EMOJI_TROFEU =
            "\uD83C\uDFC6";

    private static final String EMOJI_EXPLOSAO =
            "\uD83D\uDCA5";

    private static final String EMOJI_RELOGIO =
            "\u23F1";

    private static final String EMOJI_JOGADA =
            "\uD83D\uDC46";

    private static final String EMOJI_ICONE_ESTATISTICA =
            EMOJI_BOMBA;

    private static final Color[] CORES_NUMEROS = {
            null,
            new Color(25, 118, 210),
            new Color(56, 142, 60),
            new Color(211, 47, 47),
            new Color(13, 71, 161),
            new Color(136, 14, 14),
            new Color(0, 131, 143),
            new Color(33, 33, 33),
            new Color(97, 97, 97)
    };

    private AcoesJogador ouvinte;
    private JButton[][] botoes;
    private JLabel labelStatus;

    private JLabel lblTempo;
    private JLabel lblMinasRestantes;
    private JLabel lblCelulasReveladas;
    private JLabel lblJogadas;
    private JLabel lblVidas;
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

    public CampoMinadoView() {
        super("Campo Minado");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(COR_FUNDO);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void setOuvinte(AcoesJogador ouvinte) {
        this.ouvinte = ouvinte;
    }

    // ================================================================
    // TELA INICIAL
    // ================================================================

    public void mostrarTelaInicial() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JPanel painelCentral =
                new JPanel(new GridBagLayout());

        painelCentral.setBackground(corFundo);
        painelCentral.setBorder(
                BorderFactory.createEmptyBorder(
                        40, 60, 40, 60
                )
        );

        JPanel painelConteudo = new JPanel();

        painelConteudo.setLayout(
                new BoxLayout(
                        painelConteudo,
                        BoxLayout.Y_AXIS
                )
        );

        painelConteudo.setBackground(corFundo);
        painelConteudo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel titulo =
                new JLabel(
                        EMOJI_BOMBA + " Campo Minado"
                );

        titulo.setFont(fonteTitulo);
        titulo.setForeground(corTextoPrincipal);
        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        painelConteudo.add(titulo);

        JLabel subtitulo =
                new JLabel(
                        "Escolha sua dificuldade"
                );

        subtitulo.setFont(fonteNormal);
        subtitulo.setForeground(
                corTextoSecundario
        );

        subtitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        subtitulo.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 0, 30, 0
                )
        );

        painelConteudo.add(subtitulo);

        JPanel painelCards =
                new JPanel(
                        new GridLayout(1, 3, 15, 0)
                );

        painelCards.setBackground(corFundo);
        painelCards.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        painelCards.add(
                criarCardDificuldade(
                        "Iniciante",
                        "9 × 9",
                        "10 minas",
                        9,
                        9,
                        10
                )
        );

        painelCards.add(
                criarCardDificuldade(
                        "Intermediário",
                        "16 × 16",
                        "40 minas",
                        16,
                        16,
                        40
                )
        );

        painelCards.add(
                criarCardDificuldade(
                        "Avançado",
                        "16 × 30",
                        "99 minas",
                        16,
                        30,
                        99
                )
        );

        painelConteudo.add(painelCards);

        JLabel dica =
                new JLabel(
                        "<html><center>\uD83D\uDDB1\uFE0F "
                                + "Esquerdo: revelar • "
                                + "Direito: bandeira</center></html>"
                );

        dica.setFont(fontePequena);
        dica.setForeground(corTextoSecundario);
        dica.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        dica.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 0, 0, 0
                )
        );

        painelConteudo.add(dica);
        painelConteudo.add(
                Box.createVerticalStrut(20)
        );

        painelConteudo.add(
                criarPainelOpcoes()
        );

        painelCentral.add(painelConteudo);

        add(
                painelCentral,
                BorderLayout.CENTER
        );

        pack();
        setLocationRelativeTo(null);
        revalidate();
        repaint();
    }

    private JPanel criarPainelOpcoes() {
        JPanel painel = new JPanel();

        painel.setLayout(
                new BoxLayout(
                        painel,
                        BoxLayout.Y_AXIS
                )
        );

        painel.setBackground(corFundo);
        painel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JPanel linha1 =
                criarLinhaSelecao(
                        "Tema de fundo:",
                        TEMAS_FUNDO
                );

        comboTemaFundo =
                (JComboBox<String>)
                        linha1.getClientProperty(
                                "combo"
                        );

        comboTemaFundo.setSelectedItem(
                detectarTemaFundoDoSistema()
        );

        painel.add(linha1);
        painel.add(
                Box.createVerticalStrut(10)
        );

        JPanel linhaVisual =
                criarLinhaSelecao(
                        "Tema visual:",
                        TEMAS_VISUAIS
                );

        comboTemaVisual =
                (JComboBox<String>)
                        linhaVisual.getClientProperty(
                                "combo"
                        );

        painel.add(linhaVisual);
        painel.add(
                Box.createVerticalStrut(10)
        );

        JPanel linha2 =
                criarLinhaSelecao(
                        "Cor do tabuleiro:",
                        TEMAS_TABULEIRO
                );

        comboTemaTabuleiro =
                (JComboBox<String>)
                        linha2.getClientProperty(
                                "combo"
                        );

        painel.add(linha2);
        painel.add(
                Box.createVerticalStrut(10)
        );

        JPanel linha3 =
                criarLinhaSelecao(
                        "Tempo rápido:",
                        TEMPOS_JOGO
                );

        comboTempo =
                (JComboBox<String>)
                        linha3.getClientProperty(
                                "combo"
                        );

        painel.add(linha3);
        painel.add(
                Box.createVerticalStrut(10)
        );

        JButton btnTutorial =
                new JButton("Ver tutorial");

        btnTutorial.setFont(fonteNormal);
        btnTutorial.setForeground(
                corTextoPrincipal
        );
        btnTutorial.setBackground(
                corFundoClaro
        );
        btnTutorial.setFocusPainted(false);

        btnTutorial.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corBorda
                        ),
                        BorderFactory.createEmptyBorder(
                                8, 16, 8, 16
                        )
                )
        );

        btnTutorial.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        btnTutorial.addActionListener(
                e -> mostrarTutorial()
        );

        btnTutorial.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(
                            MouseEvent e) {
                        btnTutorial.setBackground(
                                corCardHover
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {
                        btnTutorial.setBackground(
                                corFundoClaro
                        );
                    }
                }
        );

        painel.add(btnTutorial);

        return painel;
    }

    private JPanel criarLinhaSelecao(
            String texto,
            String[] opcoes) {

        JPanel painel =
                new JPanel(
                        new BorderLayout(10, 0)
                );

        painel.setBackground(corFundo);
        painel.setMaximumSize(
                new Dimension(320, 40)
        );

        JLabel lbl =
                new JLabel(texto);

        lbl.setFont(fontePequena);
        lbl.setForeground(
                corTextoSecundario
        );

        painel.add(
                lbl,
                BorderLayout.WEST
        );

        JComboBox<String> combo =
                new JComboBox<>(opcoes);

        combo.setFont(fontePequena);
        combo.setBackground(
                corFundoClaro
        );
        combo.setForeground(
                corTextoPrincipal
        );

        combo.setBorder(
                BorderFactory.createLineBorder(
                        corBorda
                )
        );

        painel.add(
                combo,
                BorderLayout.EAST
        );

        painel.putClientProperty(
                "combo",
                combo
        );

        return painel;
    }

    private void mostrarTutorial() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JPanel painel = new JPanel();

        painel.setLayout(
                new BoxLayout(
                        painel,
                        BoxLayout.Y_AXIS
                )
        );

        painel.setBackground(corFundo);
        painel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 25, 25, 25
                )
        );

        JLabel titulo =
                new JLabel(
                        "Como jogar Campo Minado"
                );

        titulo.setFont(fonteTitulo);
        titulo.setForeground(
                corTextoPrincipal
        );

        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        painel.add(titulo);

        painel.add(
                Box.createVerticalStrut(20)
        );

        String texto =
                "1. Escolha uma dificuldade e um tempo rápido.\n"
                        + "2. Clique com o botão esquerdo para revelar uma célula.\n"
                        + "3. Clique com o botão direito para marcar/desmarcar uma bandeira.\n"
                        + "4. Revele todas as células sem minas para vencer.\n"
                        + "5. Ao clicar em uma mina, você perde uma vida; o jogo só termina quando as vidas acabam.\n"
                        + "6. O tempo selecionado limita a partida; se chegar a zero, você perde.\n";

        JTextArea area =
                new JTextArea(texto);

        area.setFont(fonteNormal);
        area.setForeground(
                corTextoPrincipal
        );
        area.setBackground(
                corFundoClaro
        );
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        area.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10
                )
        );

        painel.add(area);

        painel.add(
                Box.createVerticalStrut(15)
        );

        JLabel dicas =
                new JLabel(
                        "Dicas: use bandeiras para marcar minas e tente abrir áreas sem números."
                );

        dicas.setFont(fontePequena);
        dicas.setForeground(
                corTextoSecundario
        );

        dicas.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        painel.add(dicas);

        painel.add(
                Box.createVerticalStrut(25)
        );

        JButton voltar =
                new JButton("Voltar");

        voltar.setFont(fonteNormal);
        voltar.setForeground(
                corTextoPrincipal
        );
        voltar.setBackground(
                corFundoClaro
        );
        voltar.setFocusPainted(false);

        voltar.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corBorda
                        ),
                        BorderFactory.createEmptyBorder(
                                8, 16, 8, 16
                        )
                )
        );

        voltar.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        voltar.addActionListener(
                e -> mostrarTelaInicial()
        );

        painel.add(voltar);

        add(
                painel,
                BorderLayout.CENTER
        );

        pack();
        setLocationRelativeTo(null);
        revalidate();
        repaint();
    }

    public int getTempoLimiteSegundosSelecionado() {
        if (comboTempo == null) {
            return 0;
        }

        String selecionado =
                (String) comboTempo.getSelectedItem();

        if (selecionado == null
                || selecionado.startsWith("Sem")) {
            return 0;
        }

        if (selecionado.contains("1 minuto")) {
            return 60;
        }

        if (selecionado.contains("2 minutos")) {
            return 120;
        }

        if (selecionado.contains("3 minutos")) {
            return 180;
        }

        if (selecionado.contains("5 minutos")) {
            return 300;
        }

        return 0;
    }

    private String detectarTemaFundoDoSistema() {
        Color fundoSistema =
                UIManager.getColor(
                        "Panel.background"
                );

        if (fundoSistema == null) {
            fundoSistema =
                    UIManager.getColor("control");
        }

        if (fundoSistema == null) {
            return "Claro";
        }

        int luminosidade =
                (299 * fundoSistema.getRed()
                        + 587 * fundoSistema.getGreen()
                        + 114 * fundoSistema.getBlue())
                        / 1000;

        return luminosidade < 128
                ? "Escuro"
                : "Claro";
    }

    public void aplicarTemaSelecionado() {
        if (comboTemaFundo != null) {
            String tema =
                    (String) comboTemaFundo.getSelectedItem();

            if ("Claro".equals(tema)) {
                corFundo =
                        new Color(245, 245, 250);

                corFundoClaro =
                        new Color(230, 230, 235);

                corTextoPrincipal =
                        new Color(25, 25, 30);

                corTextoSecundario =
                        new Color(95, 95, 110);

                corCard =
                        new Color(245, 245, 250);

                corCardHover =
                        new Color(225, 225, 235);

                corDestaque =
                        new Color(35, 100, 190);

                corBorda =
                        new Color(180, 180, 190);

            } else if ("Campo".equals(tema)) {
                corFundo =
                        new Color(25, 35, 25);

                corFundoClaro =
                        new Color(45, 65, 45);

                corTextoPrincipal =
                        new Color(220, 230, 200);

                corTextoSecundario =
                        new Color(170, 190, 150);

                corCard =
                        new Color(35, 55, 35);

                corCardHover =
                        new Color(55, 75, 55);

                corDestaque =
                        new Color(140, 200, 120);

                corBorda =
                        new Color(60, 80, 60);

            } else {
                corFundo = COR_FUNDO;
                corFundoClaro = COR_FUNDO_CLARO;
                corTextoPrincipal =
                        COR_TEXTO_PRINCIPAL;
                corTextoSecundario =
                        COR_TEXTO_SECUNDARIO;
                corCard = COR_CARD;
                corCardHover =
                        COR_CARD_HOVER;
                corDestaque =
                        COR_DESTAQUE;
                corBorda = COR_BORDA;
            }
        }

        if (comboTemaTabuleiro != null) {
            String tema =
                    (String) comboTemaTabuleiro
                            .getSelectedItem();

            if ("Noite".equals(tema)) {
                corCelulaOculta =
                        new Color(20, 30, 45);

                corCelulaOcultaHover =
                        new Color(35, 50, 75);

                corBordaOculta =
                        new Color(70, 90, 120);

                corCelulaRevelada =
                        new Color(55, 65, 80);

                corBordaRevelada =
                        new Color(80, 95, 115);

                corTextoSobreRevelada =
                        new Color(230, 230, 240);

                corMinaFundo =
                        new Color(180, 40, 40);

            } else if ("Verde".equals(tema)) {
                corCelulaOculta =
                        new Color(40, 70, 45);

                corCelulaOcultaHover =
                        new Color(60, 95, 65);

                corBordaOculta =
                        new Color(70, 105, 80);

                corCelulaRevelada =
                        new Color(220, 235, 210);

                corBordaRevelada =
                        new Color(155, 175, 145);

                corTextoSobreRevelada =
                        new Color(25, 45, 25);

                corMinaFundo =
                        new Color(170, 40, 40);

            } else {
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
            }
        }

        aplicarTemaVisualSelecionado();

        getContentPane().setBackground(
                corFundo
        );
    }

    private void aplicarTemaVisualSelecionado() {
        fonteCelula = FONTE_CELULA;
        fonteTitulo = FONTE_TITULO;
        fonteSubtitulo = FONTE_SUBTITULO;
        fonteNormal = FONTE_NORMAL;
        fonteNumero = FONTE_NUMERO;
        fontePequena = FONTE_PEQUENA;

        corMina = COR_MINA;
        corVitoria = COR_VITORIA;
        corBandeira = COR_BANDEIRA;
        coresNumeros = CORES_NUMEROS;

        if (comboTemaVisual == null) {
            return;
        }

        String tema =
                (String) comboTemaVisual
                        .getSelectedItem();

        boolean fundoClaro =
                "Claro".equals(
                        comboTemaFundo != null
                                ? comboTemaFundo.getSelectedItem()
                                : null
                );

        if ("Cyberpunk Neon".equals(tema)) {
            corDestaque =
                    new Color(0, 190, 205);

            corBorda =
                    new Color(225, 30, 165);

            corMina =
                    new Color(225, 45, 105);

            corVitoria =
                    new Color(25, 180, 125);

            corBandeira =
                    new Color(210, 145, 20);

            corCelulaOcultaHover =
                    fundoClaro
                            ? new Color(225, 205, 235)
                            : new Color(67, 22, 91);

            corBordaOculta =
                    fundoClaro
                            ? new Color(180, 60, 190)
                            : new Color(170, 30, 210);

            corBordaRevelada =
                    fundoClaro
                            ? new Color(0, 170, 190)
                            : new Color(0, 220, 235);

            corMinaFundo =
                    fundoClaro
                            ? new Color(255, 220, 235)
                            : new Color(90, 12, 48);

            coresNumeros =
                    new Color[]{
                            null,
                            new Color(0, 155, 175),
                            new Color(25, 160, 100),
                            new Color(205, 35, 125),
                            new Color(120, 55, 190),
                            new Color(190, 105, 15),
                            new Color(0, 130, 170),
                            new Color(180, 50, 145),
                            new Color(80, 70, 150)
                    };

            if (fundoClaro) {
                corFundo =
                        new Color(248, 242, 252);

                corFundoClaro =
                        new Color(255, 250, 255);

                corTextoPrincipal =
                        new Color(45, 20, 55);

                corTextoSecundario =
                        new Color(100, 65, 115);

                corCard =
                        new Color(250, 244, 255);

                corCardHover =
                        new Color(238, 222, 248);

                corCelulaOculta =
                        new Color(232, 218, 240);

                corCelulaRevelada =
                        new Color(255, 255, 255);

                corTextoSobreRevelada =
                        new Color(45, 25, 55);

            } else {
                corFundo =
                        new Color(18, 8, 30);

                corFundoClaro =
                        new Color(31, 14, 48);

                corTextoPrincipal =
                        new Color(245, 225, 255);

                corTextoSecundario =
                        new Color(190, 150, 210);

                corCard =
                        new Color(37, 15, 55);

                corCardHover =
                        new Color(57, 20, 80);

                corCelulaOculta =
                        new Color(39, 16, 61);

                corCelulaRevelada =
                        new Color(58, 29, 76);

                corTextoSobreRevelada =
                        new Color(235, 245, 255);
            }

        } else if ("Terminal Retro".equals(tema)) {
            corDestaque =
                    new Color(35, 180, 55);

            corBorda =
                    new Color(40, 145, 50);

            corMina =
                    new Color(190, 45, 45);

            corVitoria =
                    new Color(45, 190, 65);

            corBandeira =
                    new Color(125, 165, 45);

            corCelulaOcultaHover =
                    fundoClaro
                            ? new Color(205, 235, 205)
                            : new Color(12, 52, 12);

            corBordaOculta =
                    fundoClaro
                            ? new Color(45, 160, 55)
                            : new Color(55, 180, 55);

            corBordaRevelada =
                    fundoClaro
                            ? new Color(70, 155, 70)
                            : new Color(70, 210, 70);

            corMinaFundo =
                    fundoClaro
                            ? new Color(250, 220, 220)
                            : new Color(55, 15, 15);

            coresNumeros =
                    new Color[]{
                            null,
                            new Color(25, 145, 40),
                            new Color(35, 160, 50),
                            new Color(175, 40, 40),
                            new Color(70, 100, 55),
                            new Color(125, 110, 30),
                            new Color(25, 125, 55),
                            new Color(80, 125, 45),
                            new Color(60, 100, 65)
                    };

            fonteCelula =
                    new Font(
                            Font.MONOSPACED,
                            Font.BOLD,
                            20
                    );

            fonteTitulo =
                    new Font(
                            Font.MONOSPACED,
                            Font.BOLD,
                            28
                    );

            fonteSubtitulo =
                    new Font(
                            Font.MONOSPACED,
                            Font.BOLD,
                            16
                    );

            fonteNormal =
                    new Font(
                            Font.MONOSPACED,
                            Font.PLAIN,
                            14
                    );

            fonteNumero =
                    new Font(
                            Font.MONOSPACED,
                            Font.BOLD,
                            18
                    );

            fontePequena =
                    new Font(
                            Font.MONOSPACED,
                            Font.PLAIN,
                            12
                    );

            if (fundoClaro) {
                corFundo =
                        new Color(244, 249, 244);

                corFundoClaro =
                        new Color(252, 255, 252);

                corTextoPrincipal =
                        new Color(20, 75, 25);

                corTextoSecundario =
                        new Color(45, 115, 50);

                corCard =
                        new Color(240, 248, 240);

                corCardHover =
                        new Color(220, 238, 220);

                corCelulaOculta =
                        new Color(215, 235, 215);

                corCelulaRevelada =
                        new Color(255, 255, 255);

                corTextoSobreRevelada =
                        new Color(20, 70, 25);

            } else {
                corFundo =
                        new Color(5, 12, 5);

                corFundoClaro =
                        new Color(10, 25, 10);

                corTextoPrincipal =
                        new Color(125, 255, 125);

                corTextoSecundario =
                        new Color(70, 190, 70);

                corCard =
                        new Color(8, 22, 8);

                corCardHover =
                        new Color(15, 40, 15);

                corCelulaOculta =
                        new Color(8, 32, 8);

                corCelulaRevelada =
                        new Color(15, 45, 15);

                corTextoSobreRevelada =
                        new Color(125, 255, 125);
            }
        }
    }

    private JPanel criarCardDificuldade(
            String titulo,
            String dimensao,
            String minasTexto,
            int linhas,
            int colunas,
            int minas) {

        JPanel card = new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBackground(corCard);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corBorda,
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                20, 25, 20, 25
                        )
                )
        );

        card.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        card.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel lblTitulo =
                new JLabel(titulo);

        lblTitulo.setFont(fonteSubtitulo);
        lblTitulo.setForeground(corDestaque);
        lblTitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(lblTitulo);

        JLabel lblDim =
                new JLabel(dimensao);

        lblDim.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        lblDim.setForeground(
                corTextoPrincipal
        );

        lblDim.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lblDim.setBorder(
                BorderFactory.createEmptyBorder(
                        8, 0, 4, 0
                )
        );

        card.add(lblDim);

        JLabel lblMinas =
                new JLabel(
                        EMOJI_BOMBA
                                + " "
                                + minasTexto
                );

        lblMinas.setFont(fonteNormal);
        lblMinas.setForeground(
                corTextoSecundario
        );

        lblMinas.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(lblMinas);

        card.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        card.setBackground(
                                corCardHover
                        );

                        card.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                corDestaque,
                                                2
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                19, 24, 19, 24
                                        )
                                )
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        card.setBackground(
                                corCard
                        );

                        card.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                corBorda,
                                                1
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                20, 25, 20, 25
                                        )
                                )
                        );
                    }

                    @Override
                    public void mouseClicked(
                            MouseEvent e) {

                        if (ouvinte != null) {
                            ouvinte.aoEscolherDificuldade(
                                    linhas,
                                    colunas,
                                    minas
                            );
                        }
                    }
                }
        );

        return card;
    }

    // ================================================================
    // TELA DE JOGO
    // ================================================================

    public void iniciarTelaDeJogo(
            int linhas,
            int colunas,
            int totalMinas,
            int totalCelulas,
            int tempoLimiteSegundos,
            int vidasRestantes) {

        getContentPane().removeAll();
        setLayout(new BorderLayout(0, 0));

        add(
                criarPainelSuperior(),
                BorderLayout.NORTH
        );

        JPanel painelPrincipal =
                new JPanel(
                        new BorderLayout(15, 0)
                );

        painelPrincipal.setBackground(
                corFundo
        );

        painelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 15, 15, 15
                )
        );

        painelPrincipal.add(
                criarPainelTabuleiro(
                        linhas,
                        colunas
                ),
                BorderLayout.CENTER
        );

        painelPrincipal.add(
                criarPainelEstatisticas(
                        totalMinas,
                        totalCelulas,
                        vidasRestantes
                ),
                BorderLayout.EAST
        );

        add(
                painelPrincipal,
                BorderLayout.CENTER
        );

        pack();
        setLocationRelativeTo(null);
        revalidate();
        repaint();
    }

    private JPanel criarPainelSuperior() {
        JPanel painel =
                new JPanel(
                        new BorderLayout()
                );

        painel.setBackground(corFundo);

        painel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 10, 15
                )
        );

        JButton btnNovo =
                new JButton("← Novo Jogo");

        btnNovo.setFont(fonteNormal);
        btnNovo.setForeground(
                corTextoPrincipal
        );

        btnNovo.setBackground(
                corFundoClaro
        );

        btnNovo.setFocusPainted(false);

        btnNovo.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corBorda
                        ),
                        BorderFactory.createEmptyBorder(
                                8, 16, 8, 16
                        )
                )
        );

        btnNovo.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        btnNovo.addActionListener(
                e -> {
                    if (ouvinte != null) {
                        ouvinte.aoPedirNovoJogo();
                    }
                }
        );

        btnNovo.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseEntered(
                            MouseEvent e) {
                        btnNovo.setBackground(
                                corCardHover
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {
                        btnNovo.setBackground(
                                corFundoClaro
                        );
                    }
                }
        );

        labelStatus =
                new JLabel(
                        "Boa sorte!",
                        SwingConstants.CENTER
                );

        labelStatus.setFont(
                fonteSubtitulo
        );

        labelStatus.setForeground(
                corTextoSecundario
        );

        painel.add(
                btnNovo,
                BorderLayout.WEST
        );

        painel.add(
                labelStatus,
                BorderLayout.CENTER
        );

        return painel;
    }

    private JPanel criarPainelEstatisticas(
            int totalMinas,
            int totalCelulas,
            int vidasRestantes) {

        JPanel painel = new JPanel();

        painel.setLayout(
                new BoxLayout(
                        painel,
                        BoxLayout.Y_AXIS
                )
        );

        painel.setBackground(
                corFundoClaro
        );

        painel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corBorda,
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                15, 15, 15, 15
                        )
                )
        );

        int largura =
                180 + Math.min(
                        100,
                        totalMinas * 2
                );

        painel.setPreferredSize(
                new Dimension(
                        largura,
                        0
                )
        );

        JLabel lblTitulo =
                new JLabel("Estatísticas");

        lblTitulo.setFont(
                fonteSubtitulo
        );

        lblTitulo.setForeground(
                corDestaque
        );

        lblTitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        painel.add(lblTitulo);

        painel.add(
                Box.createVerticalStrut(20)
        );

        JPanel pnlTempo =
                criarItemEstatistica(
                        EMOJI_RELOGIO + " Tempo",
                        "00:00"
                );

        lblTempo =
                (JLabel)
                        pnlTempo.getClientProperty(
                                "valor"
                        );

        painel.add(pnlTempo);

        painel.add(
                Box.createVerticalStrut(15)
        );

        JPanel pnlMinas =
                criarItemEstatistica(
                        EMOJI_BOMBA + " Minas",
                        String.valueOf(totalMinas)
                );

        lblMinasRestantes =
                (JLabel)
                        pnlMinas.getClientProperty(
                                "valor"
                        );

        painel.add(pnlMinas);

        painel.add(
                Box.createVerticalStrut(15)
        );

        JPanel pnlReveladas =
                criarItemEstatistica(
                        EMOJI_ICONE_ESTATISTICA
                                + " Reveladas",
                        "0 / " + totalCelulas
                );

        lblCelulasReveladas =
                (JLabel)
                        pnlReveladas.getClientProperty(
                                "valor"
                        );

        painel.add(pnlReveladas);

        painel.add(
                Box.createVerticalStrut(15)
        );

        JPanel pnlJogadas =
                criarItemEstatistica(
                        EMOJI_JOGADA + " Jogadas",
                        "0"
                );

        lblJogadas =
                (JLabel)
                        pnlJogadas.getClientProperty(
                                "valor"
                        );

        painel.add(pnlJogadas);

        painel.add(
                Box.createVerticalStrut(15)
        );

        JPanel pnlVidas =
                criarItemEstatistica(
                        "❤️ Vidas",
                        String.valueOf(vidasRestantes)
                );

        lblVidas =
                (JLabel)
                        pnlVidas.getClientProperty(
                                "valor"
                        );

        painel.add(pnlVidas);

        painel.add(
                Box.createVerticalStrut(20)
        );

        JLabel lblProgTitulo =
                new JLabel("Progresso");

        lblProgTitulo.setFont(
                fonteNormal
        );

        lblProgTitulo.setForeground(
                corTextoSecundario
        );

        lblProgTitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        painel.add(lblProgTitulo);

        barraProgresso =
                new JProgressBar(
                        0,
                        Math.max(
                                totalCelulas,
                                1
                        )
                );

        barraProgresso.setValue(0);
        barraProgresso.setStringPainted(true);
        barraProgresso.setString("0%");
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
                new Dimension(150, 20)
        );

        barraProgresso.setMaximumSize(
                new Dimension(150, 20)
        );

        barraProgresso.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        painel.add(barraProgresso);

        painel.add(
                Box.createVerticalStrut(15)
        );

        painel.add(
                Box.createVerticalGlue()
        );

        JLabel lblDica =
                new JLabel(
                        "<html><center>\uD83D\uDDB1\uFE0F "
                                + "Esquerdo: revelar<br>"
                                + "\uD83D\uDDB1\uFE0F "
                                + "Direito: bandeira</center></html>"
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

        painel.add(lblDica);

        return painel;
    }

    private JPanel criarItemEstatistica(
            String titulo,
            String valorInicial) {

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
                new JLabel(titulo);

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
                new JLabel(valorInicial);

        lblValor.setFont(
                fonteNumero
        );

        lblValor.setForeground(
                corTextoPrincipal
        );

        lblValor.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        painelItem.add(lblTitulo);
        painelItem.add(lblValor);

        painelItem.putClientProperty(
                "valor",
                lblValor
        );

        return painelItem;
    }

    private JPanel criarPainelTabuleiro(
            int linhas,
            int colunas) {

        JPanel grade =
                new JPanel(
                        new GridLayout(
                                linhas,
                                colunas,
                                2,
                                2
                        )
                );

        grade.setBackground(corFundo);

        botoes =
                new JButton[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                JButton botao =
                        criarBotaoCelula(
                                i,
                                j
                        );

                botoes[i][j] = botao;
                grade.add(botao);
            }
        }

        return grade;
    }

    private JButton criarBotaoCelula(
            int linha,
            int coluna) {

        JButton botao =
                new JButton();

        botao.setPreferredSize(
                new Dimension(36, 36)
        );

        botao.setFont(
                fonteCelula
        );

        botao.setFocusPainted(false);

        botao.setBackground(
                corCelulaOculta
        );

        botao.setForeground(
                corTextoPrincipal
        );

        botao.setMargin(
                new Insets(0, 0, 0, 0)
        );

        botao.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corBorda,
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                2, 2, 2, 2
                        )
                )
        );

        botao.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        botao.putClientProperty(
                "revelada",
                Boolean.FALSE
        );

        botao.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        if (Boolean.FALSE.equals(
                                botao.getClientProperty(
                                        "revelada"
                                ))) {

                            botao.setBackground(
                                    corCelulaOcultaHover
                            );
                        }
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        if (Boolean.FALSE.equals(
                                botao.getClientProperty(
                                        "revelada"
                                ))) {

                            botao.setBackground(
                                    corCelulaOculta
                            );
                        }
                    }

                    @Override
                    public void mouseReleased(
                            MouseEvent evento) {

                        if (ouvinte == null) {
                            return;
                        }

                        boolean botaoDireito =
                                SwingUtilities
                                        .isRightMouseButton(
                                                evento
                                        )
                                        || evento.getButton()
                                        == MouseEvent.BUTTON3;

                        if (botaoDireito) {
                            ouvinte.aoMarcarCelula(
                                    linha,
                                    coluna
                            );
                        } else if (
                                SwingUtilities
                                        .isLeftMouseButton(
                                                evento
                                        )) {

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

    // ================================================================
    // ATUALIZAÇÕES CHAMADAS PELO CONTROLLER
    // ================================================================

    public void atualizarCelula(
            int linha,
            int coluna,
            LeituraTabuleiro leitura) {

        JButton botao =
                botoes[linha][coluna];

        botao.putClientProperty(
                "revelada",
                leitura.isRevelada(
                        linha,
                        coluna
                )
        );

        if (leitura.isMarcada(
                linha,
                coluna)) {

            botao.setText(
                    EMOJI_BANDEIRA
            );

            botao.setForeground(
                    corBandeira
            );

            botao.setBackground(
                    corCelulaOculta
            );

            botao.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    corBandeira,
                                    1
                            ),
                            BorderFactory.createEmptyBorder(
                                    2, 2, 2, 2
                            )
                    )
            );

            return;
        }

        if (!leitura.isRevelada(
                linha,
                coluna)) {

            botao.setText("");
            botao.setBackground(
                    corCelulaOculta
            );

            botao.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    corBordaOculta,
                                    1
                            ),
                            BorderFactory.createEmptyBorder(
                                    2, 2, 2, 2
                            )
                    )
            );

            return;
        }

        if (leitura.isMinada(
                linha,
                coluna)) {

            botao.setText(
                    EMOJI_BOMBA
            );

            botao.setBackground(
                    corMinaFundo
            );

            botao.setForeground(
                    corMina
            );

            botao.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    corMina,
                                    1
                            ),
                            BorderFactory.createEmptyBorder(
                                    2, 2, 2, 2
                            )
                    )
            );

        } else {
            botao.setBackground(
                    corCelulaRevelada
            );

            botao.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    corBordaRevelada,
                                    1
                            ),
                            BorderFactory.createEmptyBorder(
                                    2, 2, 2, 2
                            )
                    )
            );

            int minasVizinhas =
                    leitura.getMinasVizinhas(
                            linha,
                            coluna
                    );

            if (minasVizinhas > 0) {
                botao.setText(
                        String.valueOf(
                                minasVizinhas
                        )
                );

                if (minasVizinhas
                        < coresNumeros.length
                        && coresNumeros[minasVizinhas]
                        != null) {

                    botao.setForeground(
                            coresNumeros[
                                    minasVizinhas
                            ]
                    );

                } else {
                    botao.setForeground(
                            corTextoSobreRevelada
                    );
                }

            } else {
                botao.setText("");
                botao.setForeground(
                        corTextoSobreRevelada
                );
            }

            botao.setFont(
                    fonteNumero
            );
        }
    }

    public void atualizarEstatisticas(
            int minasRestantes,
            int celulasReveladas,
            int totalCelulas,
            int jogadas,
            int vidasRestantes) {

        if (lblMinasRestantes != null) {
            lblMinasRestantes.setText(
                    String.valueOf(
                            minasRestantes
                    )
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
                    String.valueOf(jogadas)
            );
        }

        if (lblVidas != null) {
            lblVidas.setText(
                    String.valueOf(
                            vidasRestantes
                    )
            );
        }

        int progresso =
                totalCelulas > 0
                        ? (int) (
                        (celulasReveladas
                                * 100.0)
                                / totalCelulas
                )
                        : 0;

        if (barraProgresso != null) {
            barraProgresso.setValue(
                    celulasReveladas
            );

            barraProgresso.setString(
                    progresso + "%"
            );

            if (progresso < 30) {
                barraProgresso.setForeground(
                        new Color(220, 80, 80)
                );
            } else if (progresso < 70) {
                barraProgresso.setForeground(
                        new Color(220, 180, 60)
                );
            } else {
                barraProgresso.setForeground(
                        corVitoria
                );
            }
        }
    }

    public void atualizarTempo(String texto) {
        if (lblTempo != null) {
            lblTempo.setText(texto);
        }
    }

    public void mostrarVitoria() {
        if (labelStatus != null) {
            labelStatus.setText(
                    EMOJI_TROFEU
                            + " Você venceu!"
            );

            labelStatus.setForeground(
                    corVitoria
            );
        }
    }

    public void mostrarDerrota() {
        if (labelStatus != null) {
            labelStatus.setText(
                    EMOJI_EXPLOSAO
                            + " Game Over!"
            );

            labelStatus.setForeground(
                    corMina
            );
        }
    }

    public void piscarFundoDeExplosao(
            boolean ativo) {

        if (botoes == null) {
            return;
        }

        Color fundo =
                ativo
                        ? corMinaFundo
                        : corCelulaOculta;

        for (int i = 0; i < botoes.length; i++) {
            for (int j = 0; j < botoes[i].length; j++) {
                if (!Boolean.TRUE.equals(
                        botoes[i][j]
                                .getClientProperty(
                                        "revelada"
                                ))) {

                    botoes[i][j].setBackground(
                            fundo
                    );
                }
            }
        }
    }

    public void marcarMinaExplodida(
            int linha,
            int coluna) {

        if (botoes == null) {
            return;
        }

        JButton botao =
                botoes[linha][coluna];

        botao.putClientProperty(
                "revelada",
                Boolean.TRUE
        );

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
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                corMina,
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                2, 2, 2, 2
                        )
                )
        );
    }

    public void destacarCelulaVencedora(
            int linha,
            int coluna) {

        if (botoes == null) {
            return;
        }

        JButton botao =
                botoes[linha][coluna];

        botao.setBackground(
                corVitoria
        );

        botao.setForeground(
                Color.WHITE
        );
    }
}