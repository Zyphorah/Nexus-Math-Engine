package Interpreteur.ConstructeurEquation;

import java.util.Map;

import Interpreteur.AbstractNoeud;
import Interpreteur.Valeur;
import Interpreteur.Factory.Interfaces.INoeudFactory;
import Interpreteur.Factory.Registre.RegistreSymbole;
import Interpreteur.Factory.Registre.Interfaces.IRegisteSymbole;
import Interpreteur.Interfaces.IArbre;
import Interpreteur.Interfaces.IExpression;
import Parsing.ChaineOperateurs;

public class ConstructeurArbreEquation {

    private final Map<Character,INoeudFactory> _symboleMaps;
    private final ChaineOperateurs _chaineOperateurs;

    public ConstructeurArbreEquation(ChaineOperateurs chaineOperateurs, Map<Character, INoeudFactory> symboleMaps)
    {
        this._chaineOperateurs = chaineOperateurs;
        this._symboleMaps = symboleMaps;
    }

    private IExpression construireEquationSimple(String equationSimple) {

        int index = this._chaineOperateurs.trouverOperateur(equationSimple);
        
        // Si pas d'opérateur trouvé, parser avec une valeur simple
        if (index == -1) {
            Double valeur = Double.parseDouble(equationSimple.trim());
            return new Valeur(valeur);
        }
        
        String partieGauche = equationSimple.substring(0, index).trim();
        String partieDroite = equationSimple.substring(index + 1).trim();
        
        IExpression noeudGauche = construireEquationSimple(partieGauche);
        IExpression noeudDroit = construireEquationSimple(partieDroite);

        IArbre noeud = this._symboleMaps.get(equationSimple.charAt(index)).creerNoeud();
        
        noeud.ajouterExpression(noeudGauche, noeudDroit);
        
        return noeud;
    }

    public IExpression construire(String equation)
    {
        return construireEquationSimple(equation);
    }
}
