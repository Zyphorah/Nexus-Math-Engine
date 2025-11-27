package Interpreteur;

public class Multiplication extends ComposantExpression
{

    public Multiplication(Double valeur) {
        super(valeur);
    }

    @Override
    public Double Resoudre() {
        if (this.getGauche() == null || this.getDroite() == null) {
            if (this.getValeur() != null) {
                return this.getValeur();
            }
            throw new IllegalStateException("Noeuds gauche/droite non initialisés pour la multiplication, donc c'est une équation invalide");
        }

        return this.getGauche().Resoudre() * this.getDroite().Resoudre();
    }
}
