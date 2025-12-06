package REPL.Service;

import Stockage.Interfaces.IConstanteStockage;
import Stockage.Interfaces.IVarStockage;

public class GestionnaireVariable {
    
    private final IVarStockage _stockageVariable;
    private final IConstanteStockage _stockageConstante;
    private final EvaluateurExpression _evaluateur;
    
    public GestionnaireVariable(IVarStockage stockageVariable, IConstanteStockage stockageConstante, EvaluateurExpression evaluateur) {
        this._stockageVariable = stockageVariable;
        this._stockageConstante = stockageConstante;
        this._evaluateur = evaluateur;
    }

    public void traiterSaisie(String input) {
        if (input == null || input.trim().isEmpty()) {
            System.out.println("Erreur: Veuillez spécifier une variable. Utilisez 'vars' pour afficher toutes les variables.");
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

        if (!nomVariable.matches("[a-zA-Z_]\\w*")) {
            System.out.println("Erreur: Le nom de la variable '" + nomVariable + "' est invalide. Doit commencer par une lettre ou underscore.");
            return;
        }
        
        if (this._stockageConstante.existe(nomVariable)) {
            System.out.println("Erreur: '" + nomVariable + "' est une constante et ne peut pas être modifiée.");
            return;
        }
        
        try {
            Double valeur;
            
            // Essayer d'abord de parser comme nombre simple
            try {
                valeur = Double.parseDouble(valeurStr);
            } catch (NumberFormatException e) {
                // Si ce n'est pas un nombre simple, évaluer comme expression
                valeur = this._evaluateur.evaluer(valeurStr);
            }
            
            this._stockageVariable.ajouter(nomVariable, valeur);
            System.out.println(valeur);
        } catch (NumberFormatException e) {
            System.out.println("Erreur: La valeur '" + valeurStr + "' n'est pas un nombre valide.");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }    private void afficherValeur(String nom) {
        if (this._stockageVariable.existe(nom)) {
            System.out.println(nom + " = " + this._stockageVariable.obtenir(nom));
        } else if (this._stockageConstante.existe(nom)) {
            System.out.println("[const] " + nom + " = " + this._stockageConstante.obtenir(nom));
        } else {
            System.out.println("Erreur: Variable ou constante inconnue: " + nom);
        }
    }
}
