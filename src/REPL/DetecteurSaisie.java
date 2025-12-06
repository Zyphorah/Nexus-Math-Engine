package REPL;

import REPL.Registre.Interfaces.IRegistreCommande;

public class DetecteurSaisie {
    
    private static final String PATTERN_ASSIGNATION = "[a-zA-Z_]\\w*\\s*=\\s*.+";
    private final IRegistreCommande registreCommande;
    
    public DetecteurSaisie(IRegistreCommande registreCommande) {
        this.registreCommande = registreCommande;
    }
    
    public String detecterCommande(String saisie) {
        // Extraire le premier mot
        String[] parties = saisie.split("\\s+", 2);
        String premierMot = parties[0];
        
        // Vérifier d'abord si c'est une commande connue (dynamiquement)
        if (registreCommande.existe(premierMot)) {
            return premierMot.toLowerCase();
        }
        
        // Vérifier si c'est une assignation (var = valeur)
        if (saisie.matches(PATTERN_ASSIGNATION)) {
            return "var";
        }
        
        // Sinon, c'est une commande inconnue
        return premierMot;
    }
    
    public String extraireArguments(String saisie) {
        String[] parties = saisie.split("\\s+", 2);
        String premierMot = parties[0];
        
        // Si le premier mot est une commande, retourner les arguments uniquement
        if (registreCommande.existe(premierMot)) {
            return parties.length > 1 ? parties[1] : "";
        }
        
        // Si c'est une assignation, retourner la saisie complète
        if (saisie.matches(PATTERN_ASSIGNATION)) {
            return saisie;
        }
        
        // Sinon, retourner les arguments restants
        return parties.length > 1 ? parties[1] : "";
    }
}
