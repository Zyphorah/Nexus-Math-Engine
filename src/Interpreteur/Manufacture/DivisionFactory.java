package Interpreteur.Manufacture;

import Interpreteur.Division;
import Interpreteur.Interfaces.IEquation;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public class DivisionFactory implements INoeudFactory {
    @Override
    public IEquation creerNoeud() {
        return new Division();
    }
}
