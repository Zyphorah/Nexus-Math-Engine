package Interpreteur;

public class Valeur extends AbstractNoeud {
    
    private final Double _valeur; 

    public Valeur(Double valeur)
    {
        this._valeur = valeur; 
    }
    @Override
    public Double Resoudre() {
        return this._valeur;
    }
}
