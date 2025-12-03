package Stockage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Stockage.Interfaces.IVarStockage;

public class SubstituteurVariable {
    
    private final IVarStockage _stockageVariable;
    
    public SubstituteurVariable(IVarStockage stockageVariable) {
        this._stockageVariable = stockageVariable;
    }
    
    public String substituer(String equation) {
        // Pattern pour trouver les noms de variables (lettres et underscores)
        Pattern pattern = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");
        Matcher matcher = pattern.matcher(equation);
        
        StringBuffer resultat = new StringBuffer();
        
        while (matcher.find()) {
            String nomVariable = matcher.group();
            
            if (_stockageVariable.existe(nomVariable)) {
                Double valeur = _stockageVariable.obtenir(nomVariable);
                matcher.appendReplacement(resultat, valeur.toString());
            } else {
                throw new IllegalArgumentException("Variable inconnue: " + nomVariable);
            }
        }
        
        matcher.appendTail(resultat);
        return resultat.toString();
    }
}
