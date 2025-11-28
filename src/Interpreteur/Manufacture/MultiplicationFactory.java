package Interpreteur.Manufacture;


import Interpreteur.Multiplication;
import Interpreteur.Interfaces.IEquation;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public class MultiplicationFactory implements INoeudFactory {
    @Override
    public IEquation creerNoeud() {
        return new Multiplication();
    }
}
