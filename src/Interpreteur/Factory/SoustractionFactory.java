package Interpreteur.Factory;

import Interpreteur.Soustraction;
import Interpreteur.Factory.Interfaces.INoeudFactory;
import Interpreteur.Interfaces.IArbre;

public class SoustractionFactory implements INoeudFactory {

    @Override
    public IArbre creerNoeud() {
        return new Soustraction();
    }
}
