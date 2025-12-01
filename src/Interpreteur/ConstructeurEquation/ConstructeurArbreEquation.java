package Interpreteur.ConstructeurEquation;

import Interpreteur.Valeur;
import Interpreteur.Interfaces.IArbre;
import Interpreteur.Interfaces.IExpression;
import Interpreteur.Registre.Interfaces.IRegistreSymbole;
import Parsing.ChaineResponsabilite.ChaineOperateurs;
import Parsing.ChaineResponsabilite.ParentheseService;

public class ConstructeurArbreEquation {

    private final ChaineOperateurs _chaineOperateurs;
    private final ParentheseService _parentheseService;
    private final IRegistreSymbole _symboleMaps;

    public ConstructeurArbreEquation(ChaineOperateurs chaineOperateurs, IRegistreSymbole symboleMaps, ParentheseService parentheseService)
    {
        this._chaineOperateurs = chaineOperateurs;
        this._symboleMaps = symboleMaps;
        this._parentheseService = parentheseService;
    }

    public IExpression construire(String equation) {
        equation = this._parentheseService.enleverParenthesesEnglobantes(equation);

        // Une chaine de responsabilité pour gérer l'ordre des priorité
        int index = this._chaineOperateurs.trouverOperateur(equation);

        // Si aucune opérateur trouvé, parser avec une valeur simple
        if (index == -1) {
            Double valeur = Double.parseDouble(equation.trim());
            return new Valeur(valeur);
        }
        
        String partieGauche = equation.substring(0, index).trim();
        String partieDroite = equation.substring(index + 1).trim();
        
        IExpression noeudGauche = construire(partieGauche);
        IExpression noeudDroit = construire(partieDroite);

        IArbre noeud = this._symboleMaps.obtenirNoeud(equation.charAt(index));
        
        noeud.ajouterExpression(noeudGauche, noeudDroit);
        
        return noeud;
    }
}