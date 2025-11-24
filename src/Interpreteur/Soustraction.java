package Interpreteur;

import Interpreteur.Interfaces.IExpression;

public class Soustraction extends ComposantExpression implements IExpression {

    public Soustraction(Double valeur) {
        super(valeur);
    }

    @Override
    public Double Resoudre(Double equation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Resoudre'");
    }
    
}
