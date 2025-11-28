package Interpreteur.Manufacture;

import Interpreteur.Soustraction;
import Interpreteur.Interfaces.IEquation;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public class SoustractionFactory implements INoeudFactory {

    @Override
    public IEquation creerNoeud() {
        return new Soustraction();
    }
}
