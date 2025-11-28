package Interpreteur.Manufacture;

import Interpreteur.Soustraction;
import Interpreteur.Interfaces.IExpression;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public class SoustractionFactory implements INoeudFactory {

    @Override
    public IExpression creerNoeud() {
        return new Soustraction();
    }
}
