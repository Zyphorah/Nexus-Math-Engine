package REPL.Historique;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Historique {
    private static final String FICHIER = "historique.txt";
    private static final int MAX = 20;
    private List<String> pile;

    public Historique() {
        pile = new ArrayList<>();
        charger();
    }

    public void ajouter(String operation) {
        pile.add(operation);
        if (pile.size() > MAX) {
            pile.remove(0);
        }
        sauvegarder();
    }

    public void afficher() {
        if (pile.isEmpty()) {
            System.out.println("Historique vide");
            return;
        }
        System.out.println("=== Historique ===");
        for (int i = 0; i < pile.size(); i++) {
            System.out.println((i + 1) + ". " + pile.get(i));
        }
    }

    private void charger() {
        try {
            if (Files.exists(Paths.get(FICHIER))) {
                List<String> lignes = Files.readAllLines(Paths.get(FICHIER));
                int debut = Math.max(0, lignes.size() - MAX);
                for (int i = debut; i < lignes.size(); i++) {
                    String ligne = lignes.get(i).trim();
                    if (!ligne.isEmpty()) {
                        pile.add(ligne);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement: " + e.getMessage());
        }
    }

    private void sauvegarder() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER))) {
            for (String element : pile) {
                writer.write(element);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde: " + e.getMessage());
        }
    }
}
