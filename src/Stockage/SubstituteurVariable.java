package Stockage;

import java.util.regex.Pattern;

import Stockage.Interfaces.IConstanteStockage;
import Stockage.Interfaces.IVarStockage;

public class SubstituteurVariable {
    
    private final ResolveurValeur _resolveur;
    
    // Pattern pour valider les noms de variables/constances dans le fichier
    private static final Pattern PATTERN_IDENTIFIANT = Pattern.compile("[a-zA-Z_]\\w*");
    
    public SubstituteurVariable(IVarStockage stockageVariable, IConstanteStockage stockageConstante) {
        this._resolveur = new ResolveurValeur(stockageVariable, stockageConstante);
    }
    
    public String substituer(String equation) {
        return PATTERN_IDENTIFIANT.matcher(equation).replaceAll(match -> 
            this._resolveur.resoudre(match.group())
        );
    }
}
