package REPL.Commande;

import java.util.Map;
import java.util.function.Supplier;

import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;
import Stockage.Interfaces.IConstanteStockage;
import Stockage.Interfaces.IVarStockage;

public class Variable implements ICommande {

    private final Historique _historique;
    private final IVarStockage _stockageVariable;
    private final IConstanteStockage _stockageConstante;
    private final Supplier<String> _argumentsSupplier;

    public Variable(IVarStockage stockageVariable, IConstanteStockage stockageConstante, Historique historique, Supplier<String> argumentsSupplier)
    {
        this._historique = historique;
        this._stockageVariable = stockageVariable;
        this._stockageConstante = stockageConstante;
        this._argumentsSupplier = argumentsSupplier;
    }
    
    @Override
    public void execute() {
        String arguments = this._argumentsSupplier.get();
        
        // Si aucun argument, afficher toutes les variables et constantes
        if (arguments == null || arguments.trim().isEmpty()) {
            afficherTout();
            return;
        }
        
        String input = arguments.trim();
        
        // Vérifier si c'est une assignation (contient '=')
        if (input.contains("=")) {
            traiterAssignation(input);
        } else {
            afficherValeur(input);
        }
        
        this._historique.ajouter("var " + arguments);
    }
    
    private void traiterAssignation(String input) {
        String[] parties = input.split("=", 2);
        
        if (parties.length != 2) {
            System.out.println("Erreur: Format invalide. Utilisez: nomVariable = valeur");
            return;
        }
        
        String nomVariable = parties[0].trim();
        String valeurStr = parties[1].trim();
        
        if (nomVariable.isEmpty()) {
            System.out.println("Erreur: Le nom de la variable ne peut pas être vide.");
            return;
        }
        
        // Vérifier si c'est une constante (non modifiable)
        if (this._stockageConstante.existe(nomVariable)) {
            System.out.println("Erreur: '" + nomVariable + "' est une constante et ne peut pas être modifiée.");
            return;
        }
        
        try {
            Double valeur = Double.parseDouble(valeurStr);
            this._stockageVariable.ajouter(nomVariable, valeur);
            System.out.println(valeur);
        } catch (NumberFormatException e) {
            System.out.println("Erreur: La valeur '" + valeurStr + "' n'est pas un nombre valide.");
        }
    }
    
    private void afficherValeur(String nom) {
        if (this._stockageVariable.existe(nom)) {
            System.out.println(nom + " = " + this._stockageVariable.obtenir(nom));
        } else if (this._stockageConstante.existe(nom)) {
            System.out.println("[const] " + nom + " = " + this._stockageConstante.obtenir(nom));
        } else {
            System.out.println("Erreur: Variable ou constante inconnue: " + nom);
        }
    }
    
    private void afficherTout() {
        Map<String, Double> variables = this._stockageVariable.obtenirTout();
        Map<String, Double> constantes = this._stockageConstante.obtenirTout();
        
        if (variables.isEmpty() && constantes.isEmpty()) {
            System.out.println("Aucune variable ou constante stockée.");
            return;
        }
        
        // Afficher les variables
        System.out.println("=== Variables ===");
        if (variables.isEmpty()) {
            System.out.println("  (aucune)");
        } else {
            for (Map.Entry<String, Double> entry : variables.entrySet()) {
                System.out.println("  " + entry.getKey() + " = " + entry.getValue());
            }
        }
        
        // Afficher les constantes (préfixées)
        System.out.println("=== Constantes ===");
        if (constantes.isEmpty()) {
            System.out.println("  (aucune)");
        } else {
            for (Map.Entry<String, Double> entry : constantes.entrySet()) {
                System.out.println("  [const] " + entry.getKey() + " = " + entry.getValue());
            }
        }
    }
}
