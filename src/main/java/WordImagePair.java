public class WordImagePair {
    private String word;
    private String imageUrl;

    /**
     * Konstruktor zur Initialisierung eines Wort-Bild-Paares
     * @param word Das Wort, das zu einem Bild gehört
     * @param imageUrl Die URL des Bildes
     */
    public WordImagePair(String word, String imageUrl) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Wort darf nicht leer sein");
        }
        if (!isValidUrl(imageUrl)) {
            throw new IllegalArgumentException("Ungültige URL");
        }
        this.word = word;
        this.imageUrl = imageUrl;
    }

    /**
     * Überprüft, ob eine URL valide ist
     * @param url Die zu überprüfende URL
     * @return true, wenn die URL gültig ist, sonst false
     */
    private boolean isValidUrl(String url) {
        return url != null && url.startsWith("http");
    }

    public String getWord() {
        return word;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
