package Interpreteur.Manufacture;

import Interpreteur.Adition;
import Interpreteur.Interfaces.IArbre;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public class AditionFactory implements INoeudFactory {

    public IArbre creerNoeud() {
        return new Adition();
    }
}
