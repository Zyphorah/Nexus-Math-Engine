package REPL;

public class DetecteurSaisie {
    
    private static final String PATTERN_EXPRESSION = "^[0-9(].*";
    private static final String PATTERN_EXPRESSION_AVEC_OPERATEUR = ".*[+\\-*/].*";
    private static final String PATTERN_ASSIGNATION = "[a-zA-Z_]\\w*\\s*=\\s*.+";
    
    public String detecterCommande(String saisie) {
        if (saisie.matches(PATTERN_ASSIGNATION)) {
            return "var";
        } else if (saisie.matches(PATTERN_EXPRESSION) || saisie.matches(PATTERN_EXPRESSION_AVEC_OPERATEUR)) {
            return "calculer";
        } else {
            return saisie.split("\\s+", 2)[0];
        }
    }
    
    public String extraireArguments(String saisie) {
        if (saisie.matches(PATTERN_ASSIGNATION) || 
            saisie.matches(PATTERN_EXPRESSION) || 
            saisie.matches(PATTERN_EXPRESSION_AVEC_OPERATEUR)) {
            return saisie;
        } else {
            String[] parties = saisie.split("\\s+", 2);
            return parties.length > 1 ? parties[1] : "";
        }
    }
}
