package REPL.Commande;

import java.util.function.Supplier;

import Interpreteur.ConstructeurEquation.ConstructeurArbreEquation;
import Interpreteur.Interfaces.IExpression;
import Interpreteur.Registre.RegistreSymbole;
import Parsing.ChaineResponsabilite.ChaineOperateurs;
import Parsing.ChaineResponsabilite.Interfaces.IParentheseHandler;
import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;

public class Calculer implements ICommande {
    private final IParentheseHandler _ParentheseHandler;
    private final ChaineOperateurs _ChaineOperateurs;
    private final Supplier<String> _argumentsSupplier;

    public Calculer(Historique historique, IParentheseHandler parentheseHandler, ChaineOperateurs chaineOperateurs, Supplier<String> argumentsSupplier) {
        this._ChaineOperateurs = chaineOperateurs;
        this._ParentheseHandler = parentheseHandler;
        this._argumentsSupplier = argumentsSupplier;
    }

    @Override
    public void execute() {
        String equation = this._argumentsSupplier.get();
        
        if (equation == null || equation.trim().isEmpty()) {
            System.out.println("Erreur: Veuillez fournir une équation. Exemple: calculer (3 + 5) * 2");
            return;
        }

        RegistreSymbole registreSymbole = new RegistreSymbole();
        ConstructeurArbreEquation constructeurArbreEquation = new ConstructeurArbreEquation(
            this._ChaineOperateurs, 
            registreSymbole, 
            this._ParentheseHandler
        );

        IExpression noeudFinal = constructeurArbreEquation.construire(equation.trim());
        System.out.println("Résultat: " + noeudFinal.Resoudre());
    }
}
