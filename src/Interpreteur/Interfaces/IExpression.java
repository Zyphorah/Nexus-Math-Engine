package Interpreteur.Interfaces;

public interface IExpression {
    Double Resoudre();

    void ajouterExpression(IExpression gauche, IExpression droite);
}
