public class Percorso {
    private final int lunghezzaTotale;
    private final String nomePercorso;


    public Percorso(int lunghezza, String nome) {
        this.lunghezzaTotale = lunghezza;
        this.nomePercorso = nome;
        System.out.println("Percorso creato: " + nome + " (" + lunghezza + " metri)");
    }


    public int getLunghezzaTotale() {
        return lunghezzaTotale;
    }


    public String getNomePercorso() {
        return nomePercorso;
    }


    @Override
    public String toString() {
        return "Percorso{" + "nome='" + nomePercorso + '\'' + ", lunghezzaTotale=" + lunghezzaTotale + '}';
    }
}
