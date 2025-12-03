package Stockage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Stockage.Interfaces.IConstanteStockage;
import Stockage.Interfaces.IVarStockage;

public class SubstituteurVariable {
    
    private final IVarStockage _stockageVariable;
    private final IConstanteStockage _stockageConstante;
    
    public SubstituteurVariable(IVarStockage stockageVariable, IConstanteStockage stockageConstante) {
        this._stockageVariable = stockageVariable;
        this._stockageConstante = stockageConstante;
    }
    
    public String substituer(String equation) {
        Pattern pattern = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");
        Matcher matcher = pattern.matcher(equation);
        
        StringBuffer resultat = new StringBuffer();
        
        while (matcher.find()) {
            String nom = matcher.group();
            Double valeur = null;
            
            // Chercher d'abord dans les variables, puis dans les constantes
            if (_stockageVariable.existe(nom)) {
                valeur = _stockageVariable.obtenir(nom);
            } else if (_stockageConstante.existe(nom)) {
                valeur = _stockageConstante.obtenir(nom);
            } else {
                throw new IllegalArgumentException("Variable ou constante inconnue: " + nom);
            }
            
            matcher.appendReplacement(resultat, valeur.toString());
        }
        
        matcher.appendTail(resultat);
        return resultat.toString();
    }
}
