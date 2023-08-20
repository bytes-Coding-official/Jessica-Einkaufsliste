import java.util.HashSet;
import java.util.Set;

public class Einkaufsliste {
    
    /*
    * Texteingabe: Sachen hinzufügen, Sachen entfernen, Sachen ausgeben, Sachen eine Anzahl hinzufügen
    * 
    * 
    * Sache allgemein: Name, Anzahl, Preis, Gewicht, Kategorie 
    * 
    * Einkaufsliste allgemein: Speichert mehrere Sachen gleichzeitig (ohne doppelungen).
    * 
    * /Bag semantik (Bag of words) / Set semantik (Set of words)
    * 1, 1, 1, 1, 1                     {1} 
    * */
    
    private final HashSet<Lebensmittel> lebensmittel = new HashSet<>();

    public HashSet<Lebensmittel> getLebensmittel() {
        return lebensmittel;
    }
}
