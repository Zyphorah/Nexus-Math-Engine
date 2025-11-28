package Utils.Parse.Expression;

import java.util.ArrayList;
import java.util.List;

import Utils.Parse.Expression.Interfaces.IExpressionParse;

public class ExpressionParse implements IExpressionParse{

    private List<Double> extraireNombres(String equation) {
        List<Double> nombres = new ArrayList<>();   
        StringBuilder nombre = new StringBuilder();
        
        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);
            if (Character.isDigit(c)) {
                nombre.append(c);
            } else if (c != ' ') {
                if (nombre.length() > 0) {
                    nombres.add(Double.parseDouble(nombre.toString()));
                    nombre = new StringBuilder();
                }
            }
        }
        if (nombre.length() > 0) {
            nombres.add(Double.parseDouble(nombre.toString()));
        }
        return nombres;
    }

    private List<Character> extraireOperateurs(String equation) {
        List<Character> operateurs = new ArrayList<>();
        
        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);
            if (!Character.isDigit(c) && c != ' ') {
                operateurs.add(c);
            }
        }
        return operateurs;
    }
    
    public void parserEquation(String equation, List<Double> nombres, List<Character> operateurs) {
        nombres.clear();
        nombres.addAll(extraireNombres(equation));
        operateurs.clear();
        operateurs.addAll(extraireOperateurs(equation));
    }
}
