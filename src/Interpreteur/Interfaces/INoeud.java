package Interpreteur.Interfaces;

public interface INoeud extends IExpression {
    INoeud ajouterExpression(IExpression gauche, IExpression droite);
}