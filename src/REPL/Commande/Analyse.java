package REPL.Commande;

import java.util.function.Supplier;

import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;
import REPL.Service.AnalyseurExpression;
import REPL.Service.ResultatAnalyse;

public class Analyse implements ICommande {
    private final Historique _historique;
    private final AnalyseurExpression _analyseur;
    private final Supplier<String> _argumentsSupplier;

    public Analyse(Historique historique, AnalyseurExpression analyseur, Supplier<String> argumentsSupplier) {
        this._historique = historique;
        this._analyseur = analyseur;
        this._argumentsSupplier = argumentsSupplier;
    }

    @Override
    public void execute() {
        String expression = this._argumentsSupplier.get();
        
        if (expression == null || expression.trim().isEmpty()) {
            System.out.println("Erreur: Veuillez fournir une expression à analyser.");
            System.out.println("Exemple: analyse (x * x + y * y) / pi + 10");
            return;
        }
        
        ResultatAnalyse resultat = this._analyseur.analyser(expression.trim());
        resultat.afficher();
        
        this._historique.ajouter("analyse " + expression);
    }
}
