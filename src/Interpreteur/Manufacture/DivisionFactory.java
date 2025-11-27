package Interpreteur.Manufacture;

import Interpreteur.Division;
import Interpreteur.Interfaces.IExpression;
import Interpreteur.Manufacture.Interfaces.INoeudFactory;

public class DivisionFactory implements INoeudFactory {


    @Override
    public IExpression creerNoeud(Double valeur) {
        return new Division(valeur);
    }


    @Override
    public IExpression creerNoeud() {
        return new Division();
    }
}
