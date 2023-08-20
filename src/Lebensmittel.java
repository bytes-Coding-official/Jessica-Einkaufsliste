public class Lebensmittel {

    /*
     * Texteingabe: Sachen hinzufügen, Sachen entfernen, Sachen ausgeben, Sachen eine Anzahl hinzufügen
     *
     *
     * Sache allgemein: Name, Anzahl, Preis, Gewicht, Kategorie
     *
     * Einkaufsliste allgemein: Speichert mehrere Sachen gleichzeitig (ohne doppelungen).
     *
     *
     * */

    private final String name;
    private int anzahl;
    private double preis;
    private double gewicht;
    private Kategorie kategorie;

    public Lebensmittel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    @Override
    public String toString() {
        return "Lebensmittel{" +
                "name='" + name + "'" +
                ", anzahl=" + anzahl +
                ", preis=" + preis +
                ", gewicht=" + gewicht +
                ", kategorie=" + kategorie +
                '}';
    }
}
