package Interpreteur;

public class Division extends AbstractNoeud
{
    @Override
    public Double Resoudre() {
        Double denom = this.getDroite().Resoudre();
        if (denom == 0) {
            throw new ArithmeticException("Division par zéro détectée");
        }
        return this.getGauche().Resoudre() / denom;
    }
}
