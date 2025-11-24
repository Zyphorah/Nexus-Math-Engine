package Interpreteur;

import Interpreteur.Interfaces.IExpression;

public class Division extends ComposantExpression implements IExpression
{

    public Division(Double valeur) {
        super(valeur);
    }

    @Override
    public Double Resoudre(Double equation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Resoudre'");
    }
}
