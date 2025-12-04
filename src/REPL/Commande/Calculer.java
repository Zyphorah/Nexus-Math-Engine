package REPL.Commande;

import java.util.function.Supplier;

import Interpreteur.ConstructeurEquation.ConstructeurArbreEquation;
import Interpreteur.Interfaces.IExpression;
import Interpreteur.Registre.Interfaces.IRegistreSymbole;
import Parsing.ChaineResponsabilite.ChaineOperateurs;
import Parsing.ChaineResponsabilite.Interfaces.IParentheseHandler;
import REPL.Commande.interfaces.ICommande;
import REPL.Historique.Historique;
import Stockage.SubstituteurVariable;
import Stockage.Interfaces.IConstanteStockage;
import Stockage.Interfaces.IVarStockage;

public class Calculer implements ICommande {
    private final IParentheseHandler _ParentheseHandler;
    private final ChaineOperateurs _ChaineOperateurs;
    private final Supplier<String> _argumentsSupplier;
    private final SubstituteurVariable _substituteurVariable;
    private final IRegistreSymbole _registreSymbole;

    public Calculer(Historique historique, IParentheseHandler parentheseHandler, ChaineOperateurs chaineOperateurs, Supplier<String> argumentsSupplier, IVarStockage stockageVariable, IConstanteStockage stockageConstante, IRegistreSymbole registreSymbole) {
        this._ChaineOperateurs = chaineOperateurs;
        this._ParentheseHandler = parentheseHandler;
        this._argumentsSupplier = argumentsSupplier;
        this._substituteurVariable = new SubstituteurVariable(stockageVariable, stockageConstante);
        this._registreSymbole = registreSymbole;
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

            ConstructeurArbreEquation constructeurArbreEquation = new ConstructeurArbreEquation(
                this._ChaineOperateurs, 
                this._registreSymbole, 
                this._ParentheseHandler
            );

            IExpression noeudFinal = constructeurArbreEquation.construire(equationSubstituee);
            System.out.println(noeudFinal.Resoudre());
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
}
