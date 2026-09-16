package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Responsável exclusivamente por persistir e recuperar uma partida salva.
 */
public final class SalvamentoPartida {

    private static final Path ARQUIVO =
            Paths.get("partida_salva.dat");

    private SalvamentoPartida() {
    }

    public static void salvar(PartidaSalva partida) throws IOException {
        try (ObjectOutputStream saida =
                     new ObjectOutputStream(
                             new FileOutputStream(ARQUIVO.toFile()))) {
            saida.writeObject(partida);
        }
    }

    public static PartidaSalva carregar()
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream entrada =
                     new ObjectInputStream(
                             new FileInputStream(ARQUIVO.toFile()))) {
            Object objeto = entrada.readObject();

            if (!(objeto instanceof PartidaSalva)) {
                throw new IOException("Arquivo de partida inválido.");
            }

            return (PartidaSalva) objeto;
        }
    }

    public static boolean existe() {
        return Files.isRegularFile(ARQUIVO);
    }

    public static void excluir() throws IOException {
        Files.deleteIfExists(ARQUIVO);
    }
}
