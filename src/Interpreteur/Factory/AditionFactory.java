package Interpreteur.Factory;

import Interpreteur.Adition;
import Interpreteur.Factory.Interfaces.INoeudFactory;
import Interpreteur.Interfaces.IArbre;

public class AditionFactory implements INoeudFactory {

    public IArbre creerNoeud() {
        return new Adition();
    }
}
