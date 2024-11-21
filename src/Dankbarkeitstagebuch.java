import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Dankbarkeitstagebuch {
    private static final String FILE_NAME = "dankbarkeitstagebuch.txt";
    private static final String ANSI_TURQUOISE = "\u001B[38;2;122;255;195m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println(ANSI_TURQUOISE + "Willkommen in deinem ganz persönlichen Dankbarkeitstagebuch! <3" + ANSI_RESET);

        while (running) {
            System.out.println(ANSI_TURQUOISE + "\nWähle eine Option:" + ANSI_RESET);
            System.out.println("1. Neuen Eintrag hinzufügen");
            System.out.println("2. Alle Einträge anzeigen <3");
            System.out.println("3. Beenden");
            System.out.print("Eingabe: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    neuerEintrag(scanner);
                    break;
                case "2":
                    alleEintraegeAnzeigen();
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Eingabe. Bitte versuche es erneut.");
                    break;
            }
        }

        System.out.println(ANSI_TURQUOISE + "Sei dankbar für die täglichen Dinge. Es sind die kleinen Dinge, auf die es ankommt. <3 Bis morgen!" + ANSI_RESET);
        scanner.close();
    }

    private static void neuerEintrag(Scanner scanner) {
        System.out.println(ANSI_TURQUOISE + "\nWofür bist du heute dankbar? :) (Drücke Enter, um zu speichern)" + ANSI_RESET);
        String eintrag = scanner.nextLine();

        if (!eintrag.trim().isEmpty()) {
            String datum = LocalDate.now().toString();
            String kompletterEintrag = datum + " - " + eintrag;

            try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
                writer.write(kompletterEintrag + System.lineSeparator());
                System.out.println(ANSI_TURQUOISE + "Dein Eintrag wurde gespeichert." + ANSI_RESET);
            } catch (IOException e) {
                System.out.println("Fehler beim Speichern des Eintrags: " + e.getMessage());
            }
        } else {
            System.out.println("Eintrag darf nicht leer sein.");
        }
    }

    private static void alleEintraegeAnzeigen() {
        System.out.println(ANSI_TURQUOISE + "\nDeine bisherigen Einträge:" + ANSI_RESET);

        File file = new File(FILE_NAME);
        if (file.exists()) {
            try {
                List<String> eintraege = Files.readAllLines(Paths.get(FILE_NAME));
                if (eintraege.isEmpty()) {
                    System.out.println("Noch keine Einträge vorhanden.");
                } else {
                    for (String eintrag : eintraege) {
                        System.out.println(ANSI_TURQUOISE + eintrag + ANSI_RESET);
                    }
                }
            } catch (IOException e) {
                System.out.println("Fehler beim Lesen der Einträge: " + e.getMessage());
            }
        } else {
            System.out.println("Noch keine Einträge vorhanden.");
        }
    }
}
