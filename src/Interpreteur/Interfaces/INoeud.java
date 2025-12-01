package Interpreteur.Interfaces;

public interface INoeud extends IExpression
{
    void ajouterExpression(IExpression gauche, IExpression droite);
}