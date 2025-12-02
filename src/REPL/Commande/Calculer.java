package REPL.Commande;

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

    public Calculer(Historique historique, IParentheseHandler parentheseHandler, ChaineOperateurs chaineOperateurs) {
        this._ChaineOperateurs = chaineOperateurs;
        this._ParentheseHandler = parentheseHandler;
    }

    @Override
    public void execute() {
        String equationSimple = "((3 + 5) * (2 - 8)) / 2";

        ConstructeurArbreEquation constructeurArbreEquation = new ConstructeurArbreEquation(this._ChaineOperateurs, new RegistreSymbole(), this._ParentheseHandler);

        IExpression noeudFinal = constructeurArbreEquation.construire(equationSimple);
        System.out.println("Résultat: " + noeudFinal.Resoudre());
    }
}
