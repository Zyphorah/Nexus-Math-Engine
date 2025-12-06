package REPL.Service;

import Interpreteur.ConstructeurEquation.ConstructeurArbreEquation;
import Interpreteur.Interfaces.IExpression;
import Stockage.SubstituteurVariable;

public class EvaluateurExpression {
    private final ConstructeurArbreEquation _constructeur;
    private final SubstituteurVariable _substituteur;

    public EvaluateurExpression(ConstructeurArbreEquation constructeur, SubstituteurVariable substituteur) {
        this._substituteur = substituteur;
        this._constructeur = constructeur;
    }

    public Double evaluer(String expression) throws Exception {
        String equationSubstituee = this._substituteur.substituer(expression.trim());
        IExpression noeud = this._constructeur.construire(equationSubstituee);
        return noeud.Resoudre();
    }
}
