package Interpreteur.Interfaces;

public interface IEquation extends IExpression
{
    void ajouterExpression(IExpression gauche, IExpression droite);
}