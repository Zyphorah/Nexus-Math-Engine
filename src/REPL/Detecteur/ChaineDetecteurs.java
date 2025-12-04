package REPL.Detecteur;

import java.util.List;
import java.util.function.Consumer;

import REPL.Detecteur.Interfaces.IDetecteurSaisie;

public class ChaineDetecteurs {
    
    private final List<IDetecteurSaisie> _detecteurs;
    
    public ChaineDetecteurs() {
        this._detecteurs = List.of(
            new DetecteurAssignation("var"),
            new DetecteurExpression("calculer"),
            new DetecteurCommande() 
        );
    }
    
    public void traiter(String saisie, Consumer<String> setArguments, Consumer<String> executerCommande) {
        for (IDetecteurSaisie detecteur : this._detecteurs) {
            if (detecteur.correspond(saisie)) {
                setArguments.accept(detecteur.getArguments(saisie));
                executerCommande.accept(detecteur.getNomCommande(saisie));
                return;
            }
        }
    }
}
