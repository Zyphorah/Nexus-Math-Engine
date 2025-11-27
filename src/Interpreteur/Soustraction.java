package Interpreteur;

public class Soustraction extends AbstractNoeud 
{

    private final Double _valeur; 

    public Soustraction(Double valeur) {
       this._valeur = valeur;
    }

    public Soustraction()
    {
        this._valeur = null;
    }

    @Override
    public Double Resoudre() {
        if (this.getGauche() != null && this.getDroite() != null) {
            return this.getGauche().Resoudre() - this.getDroite().Resoudre();
        }
        
        return this._valeur;
    }
}
