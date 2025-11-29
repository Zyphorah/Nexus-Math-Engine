package Interpreteur.ConstructeurEquation;

import java.util.Map;

import Interpreteur.Valeur;
import Interpreteur.Factory.Interfaces.INoeudFactory;
import Interpreteur.Interfaces.IArbre;
import Interpreteur.Interfaces.IExpression;
import Parsing.ChaineResponsabilite.ChaineOperateurs;
import Parsing.ChaineResponsabilite.ParentheseService;

public class ConstructeurArbreEquation {

    private final Map<Character,INoeudFactory> _symboleMaps;
    private final ChaineOperateurs _chaineOperateurs;
    private final ParentheseService _parentheseService;

    public ConstructeurArbreEquation(ChaineOperateurs chaineOperateurs, Map<Character, INoeudFactory> symboleMaps)
    {
        this._chaineOperateurs = chaineOperateurs;
        this._symboleMaps = symboleMaps;
        this._parentheseService = new ParentheseService(symboleMaps.keySet());
    }

    public IExpression construire(String equationSimple) {
        equationSimple = this._parentheseService.enleverParenthesesEnglobantes(equationSimple);

        // Une chaine de responsabilité pour gérer l'ordre des priorité
        int index = this._chaineOperateurs.trouverOperateur(equationSimple);

        // Si aucune opérateur trouvé, parser avec une valeur simple
        if (index == -1) {
            Double valeur = Double.parseDouble(equationSimple.trim());
            return new Valeur(valeur);
        }
        
        String partieGauche = equationSimple.substring(0, index).trim();
        String partieDroite = equationSimple.substring(index + 1).trim();
        
        IExpression noeudGauche = construire(partieGauche);
        IExpression noeudDroit = construire(partieDroite);

        IArbre noeud = this._symboleMaps.get(equationSimple.charAt(index)).creerNoeud();
        
        noeud.ajouterExpression(noeudGauche, noeudDroit);
        
        return noeud;
    }
}