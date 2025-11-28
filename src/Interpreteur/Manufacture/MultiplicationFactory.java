package Interpreteur.Manufacture;


import Interpreteur.Multiplication;
import Interpreteur.Interfaces.IArbre;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public class MultiplicationFactory implements INoeudFactory {
    @Override
    public IArbre creerNoeud() {
        return new Multiplication();
    }
}
