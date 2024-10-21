import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class WordTrainerGUI {
    private WordTrainer trainer;

    public WordTrainerGUI(WordTrainer trainer) {
        this.trainer = trainer;
    }

    public void start() {
        while (trainer.hasPairsLeft()) {
            trainer.selectRandomPair();
            String imageUrl = trainer.getSelectedImageUrl();

            if (imageUrl != null) {
                String userInput = showImageAndGetInput(imageUrl);
                if (userInput != null && !userInput.isEmpty()) {
                    boolean correct = trainer.guessWord(userInput);
                    String message = correct ? "Richtig!" : "Falsch, versuch es nochmal!";
                    JOptionPane.showMessageDialog(null, message);
                } else {
                    JOptionPane.showMessageDialog(null, "Eingabe war leer. Beende das Spiel.");
                    break;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Training abgeschlossen!");
    }

    private String showImageAndGetInput(String imageUrl) {
        try {
            // Lade das Bild
            URL url = new URL(imageUrl);
            ImageIcon imageIcon = new ImageIcon(url);

            // Erstelle ein Panel mit Bild und Eingabefeld
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Bild als JLabel hinzufügen
            JLabel label = new JLabel(imageIcon);
            panel.add(label);

            // Eingabefeld für das Wort hinzufügen
            JTextField textField = new JTextField(10);
            panel.add(textField);

            // Zeige das Panel in einem JOptionPane
            int result = JOptionPane.showConfirmDialog(null, panel, "Gib das passende Wort ein:",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Wenn OK gedrückt wurde, gib den eingegebenen Text zurück
            if (result == JOptionPane.OK_OPTION) {
                return textField.getText();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Bild konnte nicht geladen werden.");
            return null;
        }
    }
}
