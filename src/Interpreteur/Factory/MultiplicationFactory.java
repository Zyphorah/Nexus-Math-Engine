package Interpreteur.Factory;


import Interpreteur.Multiplication;
import Interpreteur.Factory.Interfaces.INoeudFactory;
import Interpreteur.Interfaces.IArbre;

public class MultiplicationFactory implements INoeudFactory {
    @Override
    public IArbre creerNoeud() {
        return new Multiplication();
    }
}
