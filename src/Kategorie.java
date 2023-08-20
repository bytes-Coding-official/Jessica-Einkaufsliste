public enum Kategorie {

    OBST,
    GEMUESE,
    GETRAENKE,
    FLEISCH,
    FISCH,
    MILCHPRODUKTE,
    BACKWAREN,
    TIEFKUEHLWAREN,
    SUESSES;


    public static void printValues() {
        for (Kategorie kategorie : Kategorie.values()) {
            System.out.println(kategorie.name());
        }
    }
}
