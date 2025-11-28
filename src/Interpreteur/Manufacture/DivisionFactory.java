package Interpreteur.Manufacture;

import Interpreteur.Division;
import Interpreteur.Interfaces.IArbre;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public class DivisionFactory implements INoeudFactory {
    @Override
    public IArbre creerNoeud() {
        return new Division();
    }
}
