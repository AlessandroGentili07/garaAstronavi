import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Giudice {
    private boolean garaInCorso = false;
    private final List<Astronave> classifica = new ArrayList<>();
    private final Percorso percorso;
    private final GestoreFile gestoreFile;
    private int totalePartecipanti = 0;

    public Giudice(Percorso percorso) {
        this.percorso = Objects.requireNonNull(percorso, "percorso non può essere null");
        this.gestoreFile = new GestoreFile();
    }

    public synchronized void avviaGara(List<Astronave> partecipanti) {
        if (garaInCorso) {
            System.out.println("La gara è già in corso!");
            return;
        }
        garaInCorso = true;
        this.totalePartecipanti = partecipanti == null ? 0 : partecipanti.size();
        System.out.println("\n Diamo il via alla gara " + percorso.getNomePercorso());

        for (Astronave a : partecipanti) {
            new Thread(a).start();
        }
    }

    public synchronized void notificaAvanzamento(Astronave a) {
        if (!garaInCorso) {
            return;
        }

        System.out.println(a.getNome() + ": Percorsi " + a.getDistanzaPercorsa() + "/" + percorso.getLunghezzaTotale());

        if (a.getDistanzaPercorsa() >= percorso.getLunghezzaTotale()) {
            // Evita doppio inserimento se la stessa astronave notificasse più volte
            if (!classifica.contains(a)) {
                classifica.add(a);
                System.out.println(a.getNome() + " ha completato la gara!");
            }

            // Se tutti i partecipanti sono arrivati, termina la gara e stampa la classifica
            if (classifica.size() >= totalePartecipanti && totalePartecipanti > 0) {
                garaInCorso = false;
                terminaGara();
            }
        }
    }

    public synchronized void terminaGara() {
        if (!garaInCorso) {
            System.out.println("\n classifica finale della gara");

            for (int i = 0; i < classifica.size(); i++) {
                System.out.println((i + 1) + ". " + classifica.get(i).getNome() + " (Distanza finale: " + classifica.get(i).getDistanzaPercorsa() + ")");
            }

            gestoreFile.salvaClassifica(classifica, percorso);
        }
    }

    public boolean isGaraInCorso() {
        return garaInCorso;
    }

    @Override
    public String toString() {
        return "Giudice{" + "garaInCorso=" + garaInCorso + ", percorso=" + percorso + '}';
    }
}
