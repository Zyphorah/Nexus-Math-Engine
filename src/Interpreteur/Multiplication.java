package Interpreteur;

public class Multiplication extends AbstractNoeud
{
    @Override
    public Double Resoudre() {
        return this.getGauche().Resoudre() * this.getDroite().Resoudre();
    }
}
