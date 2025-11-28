package Interpreteur;

public class Addition extends AbstractNoeud {
    
    @Override
    public Double Resoudre() {
        return this.getGauche().Resoudre() + this.getDroite().Resoudre(); 
    }
}
