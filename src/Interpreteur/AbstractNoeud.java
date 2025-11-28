package Interpreteur;

import Interpreteur.Interfaces.IExpression;

public abstract class AbstractNoeud implements IExpression
{
    
    private IExpression _droite; 
    private IExpression _gauche;


    public void ajouterExpression(IExpression gauche, IExpression droite)
    {
        if (droite == null || gauche == null) {
            throw new IllegalArgumentException("Les noeuds fournis sont nuls");
        }

        if (this._droite == null && this._gauche == null) {
            this._gauche = gauche;
            this._droite = droite;
        } else {
            throw new IllegalStateException("Les noeuds existent déjà pour cette opération");
        }
    }

    public IExpression getDroite() {
        return this._droite;
    }

    public IExpression getGauche() {
        return this._gauche;
    }

}
