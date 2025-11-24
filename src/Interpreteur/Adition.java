package Interpreteur;

import Interpreteur.Interfaces.IExpression;

public class Adition extends ComposantExpression implements IExpression {
    
    public Adition(Double valeur)
    {
        super(valeur);
    }

    @Override
    public Double Resoudre(Double equation) {
        if (this.getValeur() != null) {
            return this.getValeur();
        }

        if (this.getGauche() == null || this.getDroite() == null) {
            throw new IllegalStateException("Noeuds gauche/droite non initialisés pour l'addition, donc c'est une équation invalide");
        }

        return this.getGauche().Resoudre(equation) + this.getDroite().Resoudre(equation);
    }
}
