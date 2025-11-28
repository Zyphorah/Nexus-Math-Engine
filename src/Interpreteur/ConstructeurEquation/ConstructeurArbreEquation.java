package Interpreteur.ConstructeurEquation;

import Interpreteur.Adition;
import Interpreteur.AbstractNoeud;

public class ConstructeurArbreEquation {

    AbstractNoeud _expression; 

    public ConstructeurArbreEquation(AbstractNoeud expression)
    {
        this._expression = expression;
    }

    private boolean contientOperateur(String expression, StringBuilder gauche, StringBuilder operateur, StringBuilder droite) {
        int indexOp = expression.indexOf("*");
        if (indexOp < 0) {
            indexOp = expression.indexOf("/");
        }
        if (indexOp < 0) {
            indexOp = expression.indexOf("+");
        }
        if (indexOp < 0) {
            indexOp = expression.indexOf("-");
        }

        if (indexOp >= 0) {
            gauche.append(expression.substring(0, indexOp).trim());
            operateur.append(expression.substring(indexOp, indexOp + 1).trim());
            droite.append(expression.substring(indexOp + 1).trim());
            return true;
        }
        return false;
    }

    void evaluer(String expression)
    {
        double a = 0; 
        double b = 0; 
        this._expression.ajouterExpression(new Adition(a), new Adition(b));
    }
}
