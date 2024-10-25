import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WordTrainer trainer;
        String fileName = "word_trainer_session.txt";

        // Lade die gespeicherte Sitzung oder starte eine neue
        try {
            trainer = FilePersistence.load(fileName);
            System.out.println("Gespeicherte Sitzung geladen.");
        } catch (IOException e) {
            // Falls keine gespeicherte Sitzung vorhanden ist, starte eine neue
            List<WordImagePair> wordImagePairs = new ArrayList<>();
            wordImagePairs.add(new WordImagePair("Hund", "https://img.freepik.com/vektoren-premium/hund-mit-hand-gezeichnet_885110-3.jpg"));
            wordImagePairs.add(new WordImagePair("Ball", "https://media.istockphoto.com/id/91712739/de/foto/fu%C3%9Fball-ball.jpg?s=612x612&w=0&k=20&c=kzXem71bcuY3AjXO5Vac9szUALvLBmgIZ3feCrDuR2c="));
            wordImagePairs.add(new WordImagePair("Apfel", "https://cdn.pixabay.com/photo/2016/10/30/18/01/apple-1783882_640.png"));
            trainer = new WordTrainer(wordImagePairs);
            System.out.println("Neue Sitzung gestartet.");
        }

        // Starte die GUI
        WordTrainerGUI gui = new WordTrainerGUI(trainer);
        gui.start();

        // Speichere die Sitzung nach Beendigung
        try {
            FilePersistence.save(trainer, fileName);
            System.out.println("Sitzung gespeichert.");
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der Sitzung: " + e.getMessage());
        }
    }
}
