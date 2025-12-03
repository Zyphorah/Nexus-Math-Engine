package REPL.Commande;

import java.util.function.Supplier;

import Interpreteur.ConstructeurEquation.ConstructeurArbreEquation;
import Interpreteur.Interfaces.IExpression;
import Interpreteur.Registre.RegistreSymbole;
import Parsing.ChaineResponsabilite.ChaineOperateurs;
import Parsing.ChaineResponsabilite.Interfaces.IParentheseHandler;
import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;
import Stockage.SubstituteurVariable;
import Stockage.Interfaces.IVarStockage;

public class Calculer implements ICommande {
    private final IParentheseHandler _ParentheseHandler;
    private final ChaineOperateurs _ChaineOperateurs;
    private final Supplier<String> _argumentsSupplier;
    private final SubstituteurVariable _substituteurVariable;

    public Calculer(Historique historique, IParentheseHandler parentheseHandler, ChaineOperateurs chaineOperateurs, Supplier<String> argumentsSupplier, IVarStockage stockageVariable) {
        this._ChaineOperateurs = chaineOperateurs;
        this._ParentheseHandler = parentheseHandler;
        this._argumentsSupplier = argumentsSupplier;
        this._substituteurVariable = new SubstituteurVariable(stockageVariable);
    }

    @Override
    public void execute() {
        String equation = this._argumentsSupplier.get();
        
        if (equation == null || equation.trim().isEmpty()) {
            System.out.println("Erreur: Veuillez fournir une équation. Exemple: calculer (3 + 5) * 2");
            return;
        }

        // Substituer les variables par leurs valeurs AVANT la construction de l'arbre
        String equationSubstituee = this._substituteurVariable.substituer(equation.trim());

        RegistreSymbole registreSymbole = new RegistreSymbole();
        ConstructeurArbreEquation constructeurArbreEquation = new ConstructeurArbreEquation(
            this._ChaineOperateurs, 
            registreSymbole, 
            this._ParentheseHandler
        );

        IExpression noeudFinal = constructeurArbreEquation.construire(equationSubstituee);
        System.out.println("Résultat: " + noeudFinal.Resoudre());
    }
}
