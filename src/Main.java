import Interpreteur.Adition;
import Interpreteur.Interfaces.IExpression;

public class Main {
    public static void main(String[] args) {

        IExpression expression = new Adition(0);

        expression.ajouterExpression(new Adition(4), new Adition(2));

        System.out.println(expression.Resoudre());
    }
}
