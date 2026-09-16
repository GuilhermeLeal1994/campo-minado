package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * Responsável pelo armazenamento local dos melhores tempos do jogo.
 *
 * Os recordes são persistidos usando Preferences, portanto permanecem
 * disponíveis entre as execuções do programa sem criar arquivos manuais.
 *
 * São armazenados até 5 recordes para cada dificuldade.
 * Cada recorde possui o nome do jogador e o tempo em segundos.
 */
public class Recordes {

    public static final String INICIANTE = "Iniciante";
    public static final String INTERMEDIARIO = "Intermediario";
    public static final String AVANCADO = "Avancado";

    private static final int LIMITE_RECORDES = 5;
    private static final int SEM_RECORDE = -1;

    private static final String CHAVE_INICIANTE =
            "recorde.iniciante";

    private static final String CHAVE_INTERMEDIARIO =
            "recorde.intermediario";

    private static final String CHAVE_AVANCADO =
            "recorde.avancado";

    private final Preferences preferencias;

    public Recordes() {
        preferencias =
                Preferences.userNodeForPackage(
                        Recordes.class
                );
    }

    /**
     * Representa um registro do ranking.
     */
    public static class Registro {

        private final String nome;
        private final int tempoSegundos;

        public Registro(
                String nome,
                int tempoSegundos
        ) {
            this.nome = nome;
            this.tempoSegundos = tempoSegundos;
        }

        public String getNome() {
            return nome;
        }

        public int getTempoSegundos() {
            return tempoSegundos;
        }
    }

    /**
     * Retorna o melhor tempo registrado para a dificuldade.
     *
     * Mantido para compatibilidade com a implementação anterior.
     *
     * @return tempo em segundos ou -1 caso ainda não exista recorde.
     */
    public int obterRecorde(String dificuldade) {

        List<Registro> registros =
                obterTop5(dificuldade);

        if (registros.isEmpty()) {
            return SEM_RECORDE;
        }

        return registros.get(0).getTempoSegundos();
    }

    /**
     * Registra o tempo somente quando ele é melhor que o
     * melhor tempo atual.
     *
     * Mantido para compatibilidade com a implementação anterior.
     *
     * @return true quando um novo melhor tempo foi registrado.
     */
    public boolean registrarSeMelhor(
            String dificuldade,
            int tempoSegundos
    ) {

        if (tempoSegundos < 0) {
            return false;
        }

        List<Registro> registros =
                obterTop5(dificuldade);

        if (registros.isEmpty()
                || tempoSegundos
                < registros.get(0).getTempoSegundos()) {

            String nome = "Jogador";

            return adicionarRecorde(
                    dificuldade,
                    nome,
                    tempoSegundos
            );
        }

        return false;
    }

    /**
     * Verifica se um determinado tempo pode entrar no Top 5.
     *
     * @return true caso ainda existam menos de 5 registros
     * ou o tempo seja melhor que o quinto colocado.
     */
    public boolean podeEntrarNoTop5(
            String dificuldade,
            int tempoSegundos
    ) {

        if (tempoSegundos < 0) {
            return false;
        }

        List<Registro> registros =
                obterTop5(dificuldade);

        if (registros.size() < LIMITE_RECORDES) {
            return true;
        }

        Registro quintoColocado =
                registros.get(LIMITE_RECORDES - 1);

        return tempoSegundos
                < quintoColocado.getTempoSegundos();
    }

    /**
     * Adiciona um novo registro ao ranking.
     *
     * O registro é inserido, os tempos são ordenados do menor
     * para o maior e somente os 5 melhores permanecem armazenados.
     *
     * @return true quando o registro foi incluído no Top 5.
     */
    public boolean adicionarRecorde(
            String dificuldade,
            String nome,
            int tempoSegundos
    ) {

        if (tempoSegundos < 0) {
            return false;
        }

        String chave = obterChave(dificuldade);

        if (chave == null) {
            return false;
        }

        if (!podeEntrarNoTop5(
                dificuldade,
                tempoSegundos
        )) {
            return false;
        }

        if (nome == null
                || nome.trim().isEmpty()) {

            nome = "Jogador";
        }

        nome = nome.trim();

        List<Registro> registros =
                obterTop5(dificuldade);

        registros.add(
                new Registro(
                        nome,
                        tempoSegundos
                )
        );

        registros.sort(
                Comparator.comparingInt(
                        Registro::getTempoSegundos
                )
        );

        if (registros.size() > LIMITE_RECORDES) {

            registros = new ArrayList<>(
                    registros.subList(
                            0,
                            LIMITE_RECORDES
                    )
            );
        }

        salvarTop5(
                dificuldade,
                registros
        );

        return true;
    }

    /**
     * Retorna os até 5 melhores registros da dificuldade.
     *
     * Os registros são retornados em ordem crescente de tempo.
     */
    public List<Registro> obterTop5(
            String dificuldade
    ) {

        List<Registro> registros =
                new ArrayList<>();

        String chave = obterChave(dificuldade);

        if (chave == null) {
            return registros;
        }

        for (int i = 0;
                i < LIMITE_RECORDES;
                i++) {

            String nome =
                    preferencias.get(
                            chave + ".nome." + i,
                            null
                    );

            int tempo =
                    preferencias.getInt(
                            chave + ".tempo." + i,
                            SEM_RECORDE
                    );

            if (nome != null
                    && tempo >= 0) {

                registros.add(
                        new Registro(
                                nome,
                                tempo
                        )
                );
            }
        }

        registros.sort(
                Comparator.comparingInt(
                        Registro::getTempoSegundos
                )
        );

        return registros;
    }

    /**
     * Remove todos os recordes de uma determinada dificuldade.
     */
    public void limparRecordes(
            String dificuldade
    ) {

        String chave =
                obterChave(dificuldade);

        if (chave == null) {
            return;
        }

        for (int i = 0;
                i < LIMITE_RECORDES;
                i++) {

            preferencias.remove(
                    chave + ".nome." + i
            );

            preferencias.remove(
                    chave + ".tempo." + i
            );
        }
    }

    /**
     * Remove todos os recordes de todas as dificuldades.
     */
    public void limparTodosOsRecordes() {

        limparRecordes(INICIANTE);
        limparRecordes(INTERMEDIARIO);
        limparRecordes(AVANCADO);
    }

    /**
     * Identifica a dificuldade a partir das características
     * do tabuleiro.
     */
    public static String identificarDificuldade(
            int linhas,
            int colunas,
            int totalMinas
    ) {

        if (linhas == 9
                && colunas == 9
                && totalMinas == 10) {

            return INICIANTE;
        }

        if (linhas == 16
                && colunas == 16
                && totalMinas == 40) {

            return INTERMEDIARIO;
        }

        if (linhas == 16
                && colunas == 30
                && totalMinas == 99) {

            return AVANCADO;
        }

        return null;
    }

    /**
     * Retorna o nome exibido para cada dificuldade.
     */
    public static String obterNomeDificuldade(
            String dificuldade
    ) {

        if (INICIANTE.equals(dificuldade)) {
            return "Iniciante";
        }

        if (INTERMEDIARIO.equals(dificuldade)) {
            return "Intermediário";
        }

        if (AVANCADO.equals(dificuldade)) {
            return "Avançado";
        }

        return "Desconhecida";
    }

    /**
     * Salva os registros usando Preferences.
     */
    private void salvarTop5(
            String dificuldade,
            List<Registro> registros
    ) {

        String chave =
                obterChave(dificuldade);

        if (chave == null) {
            return;
        }

        for (int i = 0;
                i < LIMITE_RECORDES;
                i++) {

            String chaveNome =
                    chave + ".nome." + i;

            String chaveTempo =
                    chave + ".tempo." + i;

            if (i < registros.size()) {

                Registro registro =
                        registros.get(i);

                preferencias.put(
                        chaveNome,
                        registro.getNome()
                );

                preferencias.putInt(
                        chaveTempo,
                        registro.getTempoSegundos()
                );

            } else {

                preferencias.remove(
                        chaveNome
                );

                preferencias.remove(
                        chaveTempo
                );
            }
        }
    }

    /**
     * Retorna a chave base correspondente à dificuldade.
     */
    private String obterChave(
            String dificuldade
    ) {

        if (INICIANTE.equals(dificuldade)) {
            return CHAVE_INICIANTE;
        }

        if (INTERMEDIARIO.equals(dificuldade)) {
            return CHAVE_INTERMEDIARIO;
        }

        if (AVANCADO.equals(dificuldade)) {
            return CHAVE_AVANCADO;
        }

        return null;
    }
}