package Interpreteur;

import Interpreteur.Interfaces.IExpression;

public abstract class ComposantExpression implements IExpression {
    
    private IExpression _droite; 
    private IExpression _gauche;
    private Double _valeur;

    public ComposantExpression(Double valeur)
    {
        this._valeur = valeur; 
    }

    public void ajouterExpression(IExpression droite, IExpression gauche)
    {
        if (droite == null || gauche == null) {
            throw new IllegalArgumentException("Les noeuds fournis sont nuls");
        }

        if (this._droite == null && this._gauche == null) {
            this._droite = droite;
            this._gauche = gauche;
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

    public Double getValeur() {
        return this._valeur;
    }
}
