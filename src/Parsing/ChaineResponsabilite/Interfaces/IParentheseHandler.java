package Parsing.ChaineResponsabilite.Interfaces;

public interface IParentheseHandler {
    String enleverParenthesesEnglobantes(String equation);
    int trouverDernierAuNiveauZero(String equation, char operateur);
}
