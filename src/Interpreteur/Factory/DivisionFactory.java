package Interpreteur.Factory;

import Interpreteur.Division;
import Interpreteur.Factory.Interfaces.INoeudFactory;
import Interpreteur.Interfaces.IArbre;

public class DivisionFactory implements INoeudFactory {
    @Override
    public IArbre creerNoeud() {
        return new Division();
    }
}
