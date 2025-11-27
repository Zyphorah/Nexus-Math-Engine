package Interpreteur;


public class Division extends ComposantExpression
{

    public Division(Double valeur) {
        super(valeur);
    }

    @Override
    public Double Resoudre() {
        if (this.getGauche() == null || this.getDroite() == null) {
            if (this.getValeur() != null) {
                return this.getValeur();
            }
            throw new IllegalStateException("Noeuds gauche/droite non initialisés pour la division, donc c'est une équation invalide");
        }

        Double denom = this.getDroite().Resoudre();
        if (denom == 0) {
            throw new ArithmeticException("Division par zéro détectée");
        }

        return this.getGauche().Resoudre() / denom;
    }
}
