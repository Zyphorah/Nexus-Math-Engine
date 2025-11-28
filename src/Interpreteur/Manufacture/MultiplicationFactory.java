package Interpreteur.Manufacture;


import Interpreteur.Multiplication;
import Interpreteur.Interfaces.IExpression;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public class MultiplicationFactory implements INoeudFactory {
    @Override
    public IExpression creerNoeud() {
        return new Multiplication();
    }
}
