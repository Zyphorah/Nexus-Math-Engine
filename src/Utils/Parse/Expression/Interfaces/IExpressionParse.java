package Utils.Parse.Expression.Interfaces;

import java.util.List;

public interface IExpressionParse {
    void parserEquation(String equation, List<Double> nombres, List<Character> operateurs);
}
