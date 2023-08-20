import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Optional;
import java.util.TreeMap;

public class Texteingabe {
    /*
     * Texteingabe: Lebensmittel hinzufügen, Lebensmittel entfernen, Lebensmittel ausgeben, /
     *  Lebensmittel eine Anzahl hinzufügen
     *
     *
     * Sache allgemein: Name, Anzahl, Preis, Gewicht, Kategorie
     *
     * Einkaufsliste allgemein: Speichert mehrere Lebensmittel gleichzeitig (ohne doppelungen).
     *
     *
     * */

    public static void main(String[] args) throws Exception {


        var reader = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")));
        var einkaufsliste = new Einkaufsliste();
        var eingabe = 0;
        do {
            while (true) {
                try {
                    System.out.println("""
                            Was möchten Sie tun?
                            1. Lebensmittel hinzufügen
                            2. Lebensmittel entfernen
                            3. Lebensmittel ausgeben
                            4. Lebensmittel ändern
                            5. In Datei schreiben
                            9. Beenden
                            """);
                    eingabe = Integer.parseInt(reader.readLine());
                    break;
                } catch (Exception e) {
                    System.out.println("Gib eine Zahl ein!");
                }
            }
            switch (eingabe) {
                case 1 -> add(reader, einkaufsliste);
                case 2 -> remove(reader, einkaufsliste);
                case 3 -> print(einkaufsliste);
                case 4 -> change(reader, einkaufsliste);
                case 5 -> datei(reader, einkaufsliste);
                case 9 -> System.exit(0);
                default -> System.out.println("Falsche Eingabe!");
            }
        } while (true);

    }

    private static void datei(BufferedReader reader, Einkaufsliste einkaufsliste) throws IOException {
     if(einkaufsliste.getLebensmittel().isEmpty()){
            System.out.println("Es sind keine Lebensmittel in der Liste!");
            return;
     }
         
        System.out.println("wie soll die datei heißen?");
        var name = reader.readLine();
        var file = new File(name + ".txt");
        if (file.exists()) {
            System.out.println("Die Datei existiert bereits!");
            return;
        }
        file.createNewFile();
        var writer = new FileWriter(file, Charset.forName("UTF-8"));
        writer.write("Deine Lebensmittel sind:\n");
        einkaufsliste.getLebensmittel().forEach(lebensmittel -> {
            try {
                writer.write(lebensmittel.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.flush();
        writer.close();
        System.out.println("Datei wurde erstellt!");
    }

    private static void change(BufferedReader reader, Einkaufsliste einkaufsliste) throws IOException {
        System.out.println("Welches Lebensmittel möchtest du ändern?");
        var name = reader.readLine();
        //check if name is in list
        if (einkaufsliste.getLebensmittel().stream().noneMatch(lebensmittel -> lebensmittel.getName().equalsIgnoreCase(name))) {
            System.out.println("Dieses Lebensmittel ist nicht in der Liste!");
            return;
        }

        var item = einkaufsliste.getLebensmittel().stream().filter(lebensmittel -> lebensmittel.getName().equalsIgnoreCase(name)).findFirst().get();


        int eingabe;
        do {


            try {
                System.out.println("Was möchtest du ändern?");
                System.out.println("""
                        1. Anzahl
                        2. Preis
                        3. Gewicht
                        4. Kategorie
                        9. Abbrechen
                        """);
                eingabe = Integer.parseInt(reader.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Gib eine Zahl ein!");
            }
        } while (true);

        switch (eingabe) {
            case 9 -> System.out.println("Abgebrochen!");
            case 1 -> {
                int anzahl = 0;
                while (true)
                    try {
                        System.out.println("Was ist die neue Anzahl?");

                        anzahl = Integer.parseInt(reader.readLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Gib eine Zahl ein!");
                    }
                item.setAnzahl(anzahl);
                System.out.println("Anzahl wurde geändert!");
            }
            case 2 -> {
                double preis = 0;
                while (true)
                    try {
                        System.out.println("Was ist der neue Preis?");

                        preis = Double.parseDouble(reader.readLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Gib eine Zahl ein!");
                    }
                item.setPreis(preis);
                System.out.println("Preis wurde geändert!");
            }

            case 3 -> {
                double gewicht = 0;
                while (true)
                    try {
                        System.out.println("Was ist das neue Gewicht?");
                        gewicht = Double.parseDouble(reader.readLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Gib eine Zahl ein!");
                    }
                item.setGewicht(gewicht);
                System.out.println("Gewicht wurde geändert!");
            }

            case 4 -> {

                Kategorie kategorie;
                while (true)
                    try {
                        System.out.println("Was ist die neue Kategorie?");
                        Kategorie.printValues();
                        kategorie = Kategorie.valueOf(reader.readLine().toUpperCase());
                        break;
                    } catch (Exception e) {
                        System.out.println("Gib eine gültige Kategorie ein:\n" + Kategorie.values());
                    }
                item.setKategorie(kategorie);
                System.out.println("Kategorie wurde geändert!");
            }
            default -> System.out.println("Falsche Eingabe!");
        }
    }

    private static void print(Einkaufsliste einkaufsliste) {
        System.out.println("Deine Lebensmittel sind:");

        //klassisch
        /*for (var lebensmittel : einkaufsliste.getLebensmittel()) {
            System.out.println(lebensmittel);
        }*/
        //modern
        einkaufsliste.getLebensmittel().forEach(System.out::println);

    }

    private static void remove(BufferedReader reader, Einkaufsliste einkaufsliste) throws IOException {
        System.out.println("Welches Lebensmittel möchtest du entfernen?");
        var name = reader.readLine();
        //check if name is in list
        if (einkaufsliste.getLebensmittel().stream().noneMatch(lebensmittel -> lebensmittel.getName().equalsIgnoreCase(name)))
            System.out.println("Dieses Lebensmittel ist nicht in der Liste!");
        else {
            //remove
            einkaufsliste.getLebensmittel().removeIf(lebensmittel -> lebensmittel.getName().equalsIgnoreCase(name));
            System.out.println("Lebensmittel wurde entfernt!");
        }
    }

    private static void add(BufferedReader reader, Einkaufsliste einkaufsliste) throws IOException {
        System.out.println("Wie heißt das Lebensmittel?");
        var name = reader.readLine();
        double preis = 0;
        while (true)
            try {
                System.out.println("Wie viel kostet das Lebensmittel?");

                preis = Double.parseDouble(reader.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Gib eine Zahl ein!");
            }
        double gewicht = 0;
        while (true)
            try {
                System.out.println("Wie viel wiegt das Lebensmittel?");

                gewicht = Double.parseDouble(reader.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Gib eine Zahl ein!");
            }
        int anzahl = 0;
        while (true)
            try {
                System.out.println("Wie viele Lebensmittel möchtest du hinzufügen?");
                anzahl = Integer.parseInt(reader.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Gib eine Zahl ein!");
            }


        Kategorie kategorie;
        while (true)
            try {
                System.out.println("Was ist die Kategorie?");
                Kategorie.printValues();
                kategorie = Kategorie.valueOf(reader.readLine().toUpperCase());
                break;
            } catch (Exception e) {
                System.out.println("Gib eine gültige Kategorie ein:\n" + Kategorie.values());
            }


        var lebensmittel = new Lebensmittel(name);
        lebensmittel.setPreis(preis);
        lebensmittel.setGewicht(gewicht);
        lebensmittel.setAnzahl(anzahl);
        lebensmittel.setKategorie(kategorie);

        einkaufsliste.getLebensmittel().add(lebensmittel);
    }


}
