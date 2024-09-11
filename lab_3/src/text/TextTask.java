package text;
import java.util.Scanner;

public class TextTask {
    public int getLetterNumber(char c){
        String alphabet = "абвгґдеєжзийіклмнопрстуфхцчшщьюя";
        c = Character.toLowerCase(c);
        return alphabet.indexOf(c) + 1;
    }

    public void processText(String input) {
        System.out.println("Original text: " + input);
        StringBuilder processedText = new StringBuilder();
        StringBuilder positions = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                int position = Character.toLowerCase(c) - 'a' + 1;
                processedText.append(c).append("  ");
                positions.append(position).append("  ");
            } else {
                processedText.append(c).append("  ");
                positions.append("   ");
            }
        }

        System.out.println(processedText);
        System.out.println(positions);
    }

    public void processUkrText(String input) {
        System.out.println("Original text: " + input);
        StringBuilder processedText = new StringBuilder();
        StringBuilder positions = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                int position = this.getLetterNumber(c);
                processedText.append(c).append("  ");
                positions.append(position).append("  ");
            } else {
                processedText.append(c).append("  ");
                positions.append("   ");
            }
        }

        System.out.println(processedText);
        System.out.println(positions);
    }

    public void processTextWithPunctuation(String input) {
        System.out.println("\nText with punctuation:");
        StringBuilder processedText = new StringBuilder();
        StringBuilder positions = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                int position = Character.toLowerCase(c) - 'a' + 1;
                processedText.append(c).append("  ");
                positions.append(position).append("  ");
            } else if (!Character.isDigit(c)) {
                processedText.append(c).append("  ");
                positions.append("   ");
            }
        }

        System.out.println(processedText);
        System.out.println(positions);
    }

    public boolean validatePassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        // Перевірка наявності усіх вимог
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
}
