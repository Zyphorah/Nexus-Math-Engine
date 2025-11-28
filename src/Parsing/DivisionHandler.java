package Parsing;

public class DivisionHandler extends OperateurHandler {
    @Override
    public int trouverOperateur(String equation) {
        return equation.indexOf('/');
    }
}
