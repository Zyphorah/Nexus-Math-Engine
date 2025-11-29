package Parsing.ChaineResponsabilite.Interfaces;

public interface IOperateurHandler {
    int trouverOperateur(String equation);
    void setProchain(IOperateurHandler prochain);
    int traiter(String equation);
}
