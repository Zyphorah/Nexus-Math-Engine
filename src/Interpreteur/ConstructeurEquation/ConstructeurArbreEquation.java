package Interpreteur.ConstructeurEquation;

import Interpreteur.Valeur;
import Interpreteur.Interfaces.IExpression;
import Interpreteur.Registre.Interfaces.IRegistreSymbole;
import Parsing.ChaineResponsabilite.ChaineOperateurs;
import Parsing.ChaineResponsabilite.Interfaces.IParentheseHandler;

public class ConstructeurArbreEquation {

    private final ChaineOperateurs _chaineOperateurs;
    private final IParentheseHandler _parentheseHandler;
    private final IRegistreSymbole _symboleMaps;

    public ConstructeurArbreEquation(ChaineOperateurs chaineOperateurs, IRegistreSymbole symboleMaps, IParentheseHandler parentheseHandler)
    {
        this._chaineOperateurs = chaineOperateurs;
        this._symboleMaps = symboleMaps;
        this._parentheseHandler = parentheseHandler;
    }

    public IExpression construire(String equation) {
        equation = this._parentheseHandler.enleverParenthesesEnglobantes(equation);

        // Une chaine de responsabilité pour gérer l'ordre des priorité
        int index = this._chaineOperateurs.trouverOperateur(equation);

        // Si aucune opérateur trouvé, parser avec une valeur simple
        if (index == -1) return new Valeur(Double.parseDouble(equation.trim()));

        return this._symboleMaps.obtenirNoeud(equation.charAt(index)).ajouterExpression(construire(equation.substring(0, index).trim()), construire(equation.substring(index + 1).trim()));
    }
}