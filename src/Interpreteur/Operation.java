package Interpreteur;
import java.util.function.BiFunction;

public class Operation extends AbstractNoeud {
  
    private BiFunction<Double, Double, Double> _operateur;

    public Operation(BiFunction<Double, Double, Double> operateur)
    {
        this._operateur = operateur;
    }

    @Override
    public Double Resoudre() {
        return this._operateur.apply(this.getGauche().Resoudre() , this.getDroite().Resoudre()); 
    }
}
