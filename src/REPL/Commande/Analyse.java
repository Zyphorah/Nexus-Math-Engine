package REPL.Commande;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;
import Stockage.Interfaces.IConstanteStockage;
import Stockage.Interfaces.IVarStockage;

public class Analyse implements ICommande {
    private final Historique _historique;
    private final IVarStockage _stockageVariable;
    private final IConstanteStockage _stockageConstante;
    private final Supplier<String> _argumentsSupplier;

    public Analyse(Historique historique, IVarStockage stockageVariable, IConstanteStockage stockageConstante, Supplier<String> argumentsSupplier) {
        this._historique = historique;
        this._stockageVariable = stockageVariable;
        this._stockageConstante = stockageConstante;
        this._argumentsSupplier = argumentsSupplier;
    }

    @Override
    public void execute() {
        String expression = this._argumentsSupplier.get();
        
        if (expression == null || expression.trim().isEmpty()) {
            System.out.println("Erreur: Veuillez fournir une expression à analyser.");
            System.out.println("Exemple: analyse (x * x + y * y) / pi + 10");
            return;
        }
        
        expression = expression.trim();
        
        // Compter les opérateurs
        int countPlus = compterCaractere(expression, '+');
        int countMoins = compterCaractere(expression, '-');
        int countDiv = compterCaractere(expression, '/');
        int countMult = compterCaractere(expression, '*');
        
        // Extraire nombres, variables et constantes
        List<String> nombres = new ArrayList<>();
        Set<String> variables = new HashSet<>();
        Set<String> constantes = new HashSet<>();
        
        // Pattern pour les nombres (entiers et décimaux)
        Pattern patternNombre = Pattern.compile("\\b\\d+(\\.\\d+)?\\b");
        Matcher matcherNombre = patternNombre.matcher(expression);
        while (matcherNombre.find()) {
            nombres.add(matcherNombre.group());
        }
        
        // Pattern pour les identifiants (variables ou constantes)
        Pattern patternIdent = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");
        Matcher matcherIdent = patternIdent.matcher(expression);
        while (matcherIdent.find()) {
            String nom = matcherIdent.group();
            if (this._stockageConstante.existe(nom)) {
                constantes.add(nom);
            } else if (this._stockageVariable.existe(nom)) {
                variables.add(nom);
            } else {
                // Variable non définie, on la considère comme variable
                variables.add(nom);
            }
        }
        
        // Affichage
        System.out.println(countPlus + " + " + countMoins + " - " + countDiv + " / " + countMult + " *");
        System.out.println(nombres.size() + " nombres: " + String.join(" ", nombres));
        System.out.println(variables.size() + " variables: " + String.join(" ", variables));
        System.out.println(constantes.size() + " constantes: " + String.join(" ", constantes));
        
        this._historique.ajouter("analyse " + expression);
    }
    
    private int compterCaractere(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) count++;
        }
        return count;
    }
}
