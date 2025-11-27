package Interpreteur;

public class Multiplication extends AbstractNoeud
{
    private final Double _valeur;

    public Multiplication(Double valeur) {
        this._valeur = valeur;
    }

    public Multiplication()
    {
        this._valeur = null;
    }

    @Override
    public Double Resoudre() {
        if (this.getGauche() != null && this.getDroite() != null) {
            return this.getGauche().Resoudre() * this.getDroite().Resoudre();
        }
        
        return this._valeur;
    }
}
