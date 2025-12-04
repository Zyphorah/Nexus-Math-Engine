package REPL.Detecteur;

import REPL.Detecteur.Interfaces.IDetecteurSaisie;

public class ParseurCommande implements IDetecteurSaisie {
    
    @Override
    public boolean correspond(String saisie) {
        return true; 
    }
    
    @Override
    public String getNomCommande(String saisie) {
        return saisie.split("\\s+", 2)[0];
    }
    
    @Override
    public String getArguments(String saisie) {
        String[] parties = saisie.split("\\s+", 2);
        return parties.length > 1 ? parties[1] : "";
    }
}
