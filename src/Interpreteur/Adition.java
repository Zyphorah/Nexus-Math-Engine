package Interpreteur;

import Interpreteur.Interfaces.IExpression;

public class Adition extends ComposantExpression {
    
    public Adition(double valeur)
    {
        super(valeur);
    }

    @Override
    public Double Resoudre() {
        if (this.getGauche() == null || this.getDroite() == null) {
            if (this.getValeur() != null) {
                return this.getValeur();
            }
            throw new IllegalStateException("Noeuds gauche/droite non initialisés pour l'addition, donc c'est une équation invalide");
        }

        return this.getGauche().Resoudre() + this.getDroite().Resoudre();
    }

    @Override
    public void ajouterExpression(IExpression droite, IExpression gauche) {
        super.ajouterExpression(droite, gauche);
    }
}
