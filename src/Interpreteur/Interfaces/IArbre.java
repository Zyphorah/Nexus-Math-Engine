package Interpreteur.Interfaces;

public interface IArbre extends IExpression
{
    void ajouterExpression(IExpression gauche, IExpression droite);
}