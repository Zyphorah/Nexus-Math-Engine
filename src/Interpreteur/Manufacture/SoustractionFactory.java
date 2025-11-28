package Interpreteur.Manufacture;

import Interpreteur.Soustraction;
import Interpreteur.Interfaces.IArbre;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public class SoustractionFactory implements INoeudFactory {

    @Override
    public IArbre creerNoeud() {
        return new Soustraction();
    }
}
