package REPL.Detecteur;

import REPL.Detecteur.Interfaces.IDetecteurSaisie;

public class DetecteurAssignation implements IDetecteurSaisie {
    
    private static final String PATTERN_ASSIGNATION = "[a-zA-Z_]\\w*\\s*=\\s*.+";
    
    private final String _nomCommande;
    
    public DetecteurAssignation(String nomCommande) {
        this._nomCommande = nomCommande;
    }
    
    @Override
    public boolean correspond(String saisie) {
        return saisie.matches(PATTERN_ASSIGNATION);
    }
    
    @Override
    public String getNomCommande(String saisie) {
        return this._nomCommande;
    }
    
    @Override
    public String getArguments(String saisie) {
        return saisie;
    }
}
