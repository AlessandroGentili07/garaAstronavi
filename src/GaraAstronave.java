import java.util.ArrayList;
import java.util.List;

public class GaraAstronave {
    public static void main(String[] args) {
        final Percorso marteExpress = new Percorso(200, "Sistema solare");
        final Giudice giudice = new Giudice(marteExpress);

        final Astronave a1 = new Astronave("Alpha-7", 25, marteExpress, giudice);
        final Astronave a2 = new Astronave("Beta-9", 30, marteExpress, giudice);
        final Astronave a3 = new Astronave("Gamma-5", 28, marteExpress, giudice);

        final List<Astronave> partecipanti = new ArrayList<>();
        partecipanti.add(a1);
        partecipanti.add(a2);
        partecipanti.add(a3);


        giudice.avviaGara(partecipanti);
    }
}