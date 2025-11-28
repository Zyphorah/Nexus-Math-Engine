package Interpreteur;

import Interpreteur.Interfaces.IExpression;

public class Valeur implements IExpression {

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
