package Interpreteur.Manufacture;

import Interpreteur.Adition;
import Interpreteur.Interfaces.IEquation;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public class AditionFactory implements INoeudFactory {

    public IEquation creerNoeud() {
        return new Adition();
    }
}
