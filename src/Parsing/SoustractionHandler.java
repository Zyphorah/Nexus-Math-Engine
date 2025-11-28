package Parsing;

public class SoustractionHandler extends OperateurHandler {
    @Override
    public int trouverOperateur(String equation) {
        return equation.indexOf('-');
    }
}
