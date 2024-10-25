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
        WordTrainer trainer;
        if(pairs!=null && !pairs.isEmpty()) {
            trainer = new WordTrainer(pairs);
        } else {
            List<WordImagePair> wordImagePairs = new ArrayList<>();
            wordImagePairs.add(new WordImagePair("Hund", "https://img.freepik.com/vektoren-premium/hund-mit-hand-gezeichnet_885110-3.jpg"));
            wordImagePairs.add(new WordImagePair("Ball", "https://media.istockphoto.com/id/91712739/de/foto/fu%C3%9Fball-ball.jpg?s=612x612&w=0&k=20&c=kzXem71bcuY3AjXO5Vac9szUALvLBmgIZ3feCrDuR2c="));
            wordImagePairs.add(new WordImagePair("Apfel", "https://cdn.pixabay.com/photo/2016/10/30/18/01/apple-1783882_640.png"));
            trainer = new WordTrainer(wordImagePairs);
        }
        trainer.setCorrectAttempts(correctAttempts);
        trainer.setTotalAttempts(totalAttempts);
        return trainer;
    }
}
