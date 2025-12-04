package REPL.Detecteur;

import REPL.Detecteur.Interfaces.IDetecteurSaisie;

public class DetecteurExpression implements IDetecteurSaisie {
    
    private static final String PATTERN_NOMBRE_OU_PARENTHESE = "^[0-9(].*";
    private static final String PATTERN_IDENTIFIANT_OPERATEUR = "^[a-zA-Z_]\\w*[+\\-*/].*";
    
    @Override
    public boolean correspond(String saisie) {
        return saisie.matches(PATTERN_NOMBRE_OU_PARENTHESE) || 
               saisie.matches(PATTERN_IDENTIFIANT_OPERATEUR);
    }
    
    @Override
    public String getNomCommande(String saisie) {
        return "calculer";
    }
    
    @Override
    public String getArguments(String saisie) {
        return saisie;
    }
}
