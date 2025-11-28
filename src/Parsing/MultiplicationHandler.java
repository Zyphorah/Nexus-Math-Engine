package Parsing;

public class MultiplicationHandler extends OperateurHandler {
    @Override
    public int trouverOperateur(String equation) {
        return equation.indexOf('*');
    }
}
