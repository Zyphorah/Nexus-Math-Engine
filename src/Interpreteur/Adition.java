package Interpreteur;

public class Adition extends AbstractNoeud {
    
    private final Double _valeur;

    public Adition(double valeur)
    {
        this._valeur = valeur;
    }

    public Adition()
    {
        this._valeur = null;
    }

    @Override
    public Double Resoudre() {

        if (this.getGauche() != null && this.getDroite() != null) {
            return this.getGauche().Resoudre() + this.getDroite().Resoudre();
        }
        return this._valeur;
    }
}
