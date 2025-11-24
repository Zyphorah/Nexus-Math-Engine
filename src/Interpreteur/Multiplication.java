package Interpreteur;

import Interpreteur.Interfaces.IExpression;

public class Multiplication extends ComposantExpression implements IExpression
{

    public Multiplication(Double valeur) {
        super(valeur);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Double Resoudre(Double equation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Resoudre'");
    }
    
}
