import java.io.*;
import java.util.List;
import java.util.Objects;


public class GestoreFile {
    private static final String CLASSIFICHE = "classifiche";
    private static int numeroProva = 0;

    public GestoreFile() {
        File cartella = new File(CLASSIFICHE);
        if (!cartella.exists()) {
            System.out.println("Non c'è nessuna cartella chiamata: '" + CLASSIFICHE + "' Ora la creiamo (Professoressa)");
            cartella.mkdir(); // Crea la cartella per il salvataggio delle classifiche(mkdir serve per creare la cartella se non esiste)
            System.out.println("cartella '" + CLASSIFICHE + "' creata.");
        }
        contaFileEsistenti();
    }

    private void contaFileEsistenti() {
        File cartella = new File(CLASSIFICHE);
        File[] files = cartella.listFiles();
        if (files != null) {
            numeroProva = files.length;
        }
    }


    public boolean salvaClassifica(List<Astronave> classifica, Percorso percorso) {
        if (classifica == null || classifica.isEmpty()) {
            System.err.println("Errore: Classifica vuota, impossibile salvare.");
            return false;
        }

        Objects.requireNonNull(percorso, "percorso non può essere null");

        numeroProva++;
        String nomeFile = CLASSIFICHE + "/classifica_" +
                percorso.getNomePercorso().replaceAll(" ", "_") +
                "_prova" + numeroProva + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile))) {
            writer.write("CLASSIFICA GARA ASTRONAVI\n");
            writer.write("Percorso: " + percorso.getNomePercorso() + "\n");
            writer.write("Lunghezza: " + percorso.getLunghezzaTotale() + " metri\n");

            writer.write("POSIZIONE, ASTRONAVE, DISTANZA FINALE\n");

            for (int i = 0; i < classifica.size(); i++) {
                Astronave a = classifica.get(i);
                String posizione = String.format("%-9d", (i + 1));
                String nome = String.format("%-14s", a.getNome());
                String distanza = String.format("%d/%d m",
                        a.getDistanzaPercorsa(),
                        percorso.getLunghezzaTotale());

                writer.write(posizione + " , " + nome + " , " + distanza + "\n");
            }

            writer.write("\n Vincitore: " + classifica.get(0).getNome() + "\n");

            System.out.println("\n Classifica salvata con successo in: " + nomeFile);
            return true;

        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio della classifica: " + e.getMessage());
            return false;
        }
    }


    public boolean salvaLogDettagliato(List<String> eventi, Percorso percorso) {
        Objects.requireNonNull(eventi, "eventi non può essere null");
        Objects.requireNonNull(percorso, "percorso non può essere null");

        numeroProva++;
        String nomeFile = CLASSIFICHE + "/log_" +
                percorso.getNomePercorso().replaceAll(" ", "_") +
                "_prova" + numeroProva + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile))) {
            writer.write(" Dettagli di gara\n");
            writer.write("Percorso: " + percorso.getNomePercorso() + "\n");

            for (String evento : eventi) {
                writer.write(evento + "\n");
            }

            System.out.println("I dettagli sono stati salvati in: " + nomeFile);
            return true;

        } catch (IOException e) {
            System.err.println("Errore: " + e.getMessage());
            return false;
        }
    }
}