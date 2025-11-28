package Interpreteur.Factory;

import Interpreteur.Addition;
import Interpreteur.Factory.Interfaces.INoeudFactory;
import Interpreteur.Interfaces.IArbre;

public class AditionFactory implements INoeudFactory {

    public IArbre creerNoeud() {
        return new Addition();
    }
}
