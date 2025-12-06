package REPL.Commande;

import java.util.function.Supplier;

import Interpreteur.ConstructeurEquation.ConstructeurArbreEquation;
import Interpreteur.Interfaces.IExpression;
import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;
import Stockage.SubstituteurVariable;

public class Calculer implements ICommande {
    private final Supplier<String> _argumentsSupplier;
    private final SubstituteurVariable _substituteurVariable;
    private final ConstructeurArbreEquation _constructeurArbreEquation;
    private final Historique _historique;

    public Calculer(Historique historique, SubstituteurVariable substituteurVariable, ConstructeurArbreEquation constructeurArbreEquation, Supplier<String> argumentsSupplier) {
        this._historique = historique;
        this._argumentsSupplier = argumentsSupplier;
        this._substituteurVariable = substituteurVariable;
        this._constructeurArbreEquation = constructeurArbreEquation;
    }

    @Override
    public void execute() {
        String equation = this._argumentsSupplier.get();
        
        if (equation == null || equation.trim().isEmpty()) {
            System.out.println("Erreur: Veuillez fournir une équation. Exemple: (3 + 5) * 2");
            return;
        }

        try {
            String equationSubstituee = this._substituteurVariable.substituer(equation.trim());
            IExpression noeudFinal = this._constructeurArbreEquation.construire(equationSubstituee);
            double resultat = noeudFinal.Resoudre();
            System.out.println(resultat);
            this._historique.ajouter(equation);
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
}
