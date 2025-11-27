package Interpreteur;

public class Division extends AbstractNoeud
{
    private final Double _valeur;

    public Division(Double valeur) {
        this._valeur = valeur;
    }

    public Division()
    {
        this._valeur = null;
    }

    @Override
    public Double Resoudre() {
        if (this.getGauche() != null && this.getDroite() != null) {
            Double denom = this.getDroite().Resoudre();
            if (denom == 0) {
                throw new ArithmeticException("Division par zéro détectée");
            }
            return this.getGauche().Resoudre() / denom;
        }

        return this._valeur;
    }
}
