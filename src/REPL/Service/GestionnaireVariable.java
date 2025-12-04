package REPL.Service;

import java.util.Map;

import Stockage.Interfaces.IConstanteStockage;
import Stockage.Interfaces.IVarStockage;

public class GestionnaireVariable {
    
    private final IVarStockage _stockageVariable;
    private final IConstanteStockage _stockageConstante;
    
    public GestionnaireVariable(IVarStockage stockageVariable, IConstanteStockage stockageConstante) {
        this._stockageVariable = stockageVariable;
        this._stockageConstante = stockageConstante;
    }
    
    public void traiterSaisie(String input) {
        if (input == null || input.trim().isEmpty()) {
            afficherTout();
        } else if (input.contains("=")) {
            traiterAssignation(input.trim());
        } else {
            afficherValeur(input.trim());
        }
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
        
        System.out.println("=== Variables ===");
        if (variables.isEmpty()) {
            System.out.println("  (aucune)");
        } else {
            variables.forEach((k, v) -> System.out.println("  " + k + " = " + v));
        }
        
        System.out.println("=== Constantes ===");
        if (constantes.isEmpty()) {
            System.out.println("  (aucune)");
        } else {
            constantes.forEach((k, v) -> System.out.println("  [const] " + k + " = " + v));
        }
    }
}
