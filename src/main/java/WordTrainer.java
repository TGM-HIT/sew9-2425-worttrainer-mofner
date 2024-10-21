import java.util.List;
import java.util.Random;

public class WordTrainer {
    private List<WordImagePair> wordImagePairs;
    private WordImagePair selectedPair;
    private int totalAttempts;
    private int correctAttempts;

    public WordTrainer(List<WordImagePair> wordImagePairs) {
        if (wordImagePairs == null || wordImagePairs.isEmpty()) {
            throw new IllegalArgumentException("Die Liste der Wort-Bild-Paare darf nicht leer sein.");
        }
        this.wordImagePairs = wordImagePairs;
        this.selectedPair = null;
        this.totalAttempts = 0;
        this.correctAttempts = 0;
    }

    public void selectRandomPair() {
        Random random = new Random();
        selectedPair = wordImagePairs.get(random.nextInt(wordImagePairs.size()));
    }

    public boolean guessWord(String input) {
        totalAttempts++;
        if (selectedPair.getWord().equalsIgnoreCase(input)) {
            correctAttempts++;
            wordImagePairs.remove(selectedPair); // Entferne das Paar bei richtiger Antwort
            selectedPair = null; // Paar abwählen
            return true;
        }
        return false;
    }

    public String getSelectedImageUrl() {
        return selectedPair != null ? selectedPair.getImageUrl() : null;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public int getCorrectAttempts() {
        return correctAttempts;
    }

    public boolean hasPairsLeft() {
        return !wordImagePairs.isEmpty();
    }
}
