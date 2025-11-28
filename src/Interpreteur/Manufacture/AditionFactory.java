package Interpreteur.Manufacture;

import Interpreteur.Adition;
import Interpreteur.Interfaces.IExpression;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public class AditionFactory implements INoeudFactory {

    public IExpression creerNoeud() {
        return new Adition();
    }
}
