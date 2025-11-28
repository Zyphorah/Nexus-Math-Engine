package Parsing;

public class AdditionHandler extends OperateurHandler {
    @Override
    public int trouverOperateur(String equation) {
        return equation.indexOf('+');
    }
}
