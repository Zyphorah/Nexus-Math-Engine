package Interpreteur;

public class Soustraction extends AbstractNoeud 
{
    @Override
    public Double Resoudre() {
        return this.getGauche().Resoudre() - this.getDroite().Resoudre();
    }
}
