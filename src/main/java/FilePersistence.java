import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePersistence {

    // Methode zum Speichern des WordTrainer-Zustands
    public static void save(WordTrainer trainer, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (WordImagePair pair : trainer.getWordImagePairs()) {
                writer.write(pair.getWord() + "," + pair.getImageUrl());
                writer.newLine();
            }
            writer.write("Statistik:" + trainer.getCorrectAttempts() + "/" + trainer.getTotalAttempts());
            writer.newLine();
        }
    }

    // Methode zum Laden des WordTrainer-Zustands
    public static WordTrainer load(String fileName) throws IOException {
        List<WordImagePair> pairs = new ArrayList<>();
        int correctAttempts = 0;
        int totalAttempts = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Statistik:")) {
                    String[] parts = line.substring("Statistik:".length()).split("/");
                    correctAttempts = Integer.parseInt(parts[0]);
                    totalAttempts = Integer.parseInt(parts[1]);
                } else {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String word = parts[0];
                        String imageUrl = parts[1];
                        pairs.add(new WordImagePair(word, imageUrl));
                    }
                }
            }
        }
        WordTrainer trainer = new WordTrainer(pairs);
        trainer.setCorrectAttempts(correctAttempts);
        trainer.setTotalAttempts(totalAttempts);
        return trainer;
    }
}
