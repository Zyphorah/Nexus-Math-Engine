package Interpreteur;

public class Adition extends AbstractNoeud {
    
    @Override
    public Double Resoudre() {
        return this.getGauche().Resoudre() + this.getDroite().Resoudre(); 
    }
}
