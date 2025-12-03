package REPL.Commande;

import java.util.Map;
import java.util.function.Supplier;

import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;
import Stockage.Interfaces.IVarStockage;

public class Variable implements ICommande {

    private final Historique historique;
    private final IVarStockage stockageVariable;
    private final Supplier<String> argumentsSupplier;

    public Variable(IVarStockage stockageVariable, Historique historique, Supplier<String> argumentsSupplier)
    {
        this.historique = historique;
        this.stockageVariable = stockageVariable;
        this.argumentsSupplier = argumentsSupplier;
    }
    
    @Override
    public void execute() {
        String arguments = this.argumentsSupplier.get();
        
        // Si aucun argument, afficher toutes les variables
        if (arguments == null || arguments.trim().isEmpty()) {
            afficherToutesVariables();
            return;
        }
        
        String input = arguments.trim();
        
        // Vérifier si c'est une assignation (contient '=')
        if (input.contains("=")) {
            traiterAssignation(input);
        } else {
            // Sinon, afficher la valeur de la variable demandée
            afficherVariable(input);
        }
        
        historique.ajouter("var " + arguments);
    }
    
    private void traiterAssignation(String input) {
        String[] parties = input.split("=", 2);
        
        if (parties.length != 2) {
            System.out.println("Erreur: Format invalide. Utilisez: var nomVariable = valeur");
            return;
        }
        
        String nomVariable = parties[0].trim();
        String valeurStr = parties[1].trim();
        
        if (nomVariable.isEmpty()) {
            System.out.println("Erreur: Le nom de la variable ne peut pas être vide.");
            return;
        }
        
        try {
            Double valeur = Double.parseDouble(valeurStr);
            stockageVariable.ajouter(nomVariable, valeur);
            System.out.println("Variable '" + nomVariable + "' = " + valeur);
        } catch (NumberFormatException e) {
            System.out.println("Erreur: La valeur '" + valeurStr + "' n'est pas un nombre valide.");
        }
    }
    
    private void afficherVariable(String nomVariable) {
        try {
            Double valeur = stockageVariable.obtenir(nomVariable);
            System.out.println(nomVariable + " = " + valeur);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
    
    private void afficherToutesVariables() {
        Map<String, Double> variables = stockageVariable.obtenirTout();
        
        if (variables.isEmpty()) {
            System.out.println("Aucune variable stockée.");
            return;
        }
        
        System.out.println("=== Variables stockées ===");
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }
    }
}
