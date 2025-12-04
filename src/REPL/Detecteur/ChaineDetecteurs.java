package REPL.Detecteur;

import java.util.List;
import java.util.function.Consumer;

import REPL.Detecteur.Interfaces.IParseur;

public class ChaineDetecteurs {
    
    private final List<IParseur> _parseurs;
    
    public ChaineDetecteurs() {
        this._parseurs = List.of(
            new DetecteurAssignation(),
            new DetecteurExpression(),
            new ParseurCommande() 
        );
    }
    
    public void traiter(String saisie, Consumer<String> setArguments, Consumer<String> executerCommande) {
        for (IParseur parseur : this._parseurs) {
            if (parseur.correspond(saisie)) {
                setArguments.accept(parseur.getArguments(saisie));
                executerCommande.accept(parseur.getNomCommande(saisie));
                return;
            }
        }
    }
}
